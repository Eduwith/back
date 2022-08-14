package com.example.eduwithbe.security;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
    private String secretKey = "eduwith";
    private String refreshSecretKey = "eduwithProject";

    // 토큰 유효시간 30분
    private long tokenValidTime = 30 * 60 * 1000L;

    private final UserDetailsService userDetailsService;

    // 객체 초기화, secretKey를 Base64로 인코딩한다.
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

//    // JWT 토큰 생성
//    public String createToken(String userPk, List<String> roles) {
//        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위, 보통 여기서 user를 식별하는 값을 넣는다.
//        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
//        Date now = new Date();
//        return Jwts.builder()
//                .setClaims(claims) // 정보 저장
//                .setIssuedAt(now) // 토큰 발행 시간 정보
//                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
//                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
//                // signature 에 들어갈 secret값 세팅
//                .compact();
//    }

    public Token createAccessToken(String userEmail, String name, List<String> roles) {

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        //Refresh Token
        String refreshToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();


        return Token.builder().accessToken(accessToken).refreshToken(refreshToken).email(userEmail).name(name).build();


    }

    public String validateRefreshToken(RefreshToken refreshTokenObj) {


        // refresh 객체에서 refreshToken 추출
        String refreshToken = refreshTokenObj.getRefreshToken();


        try {
            // 검증
            Jws<Claims> claims = Jwts.parser().setSigningKey(refreshSecretKey).parseClaimsJws(refreshToken);

            //refresh 토큰의 만료시간이 지나지 않았을 경우, 새로운 access 토큰을 생성합니다.
            if (!claims.getBody().getExpiration().before(new Date())) {
                return recreationAccessToken(claims.getBody().get("sub").toString(), claims.getBody().get("roles"));
            }
        } catch (Exception e) {

            //refresh 토큰이 만료되었을 경우, 로그인이 필요합니다.
            return null;

        }

        return null;
    }

    public String recreationAccessToken(String userEmail, Object roles){

        Claims claims = Jwts.claims().setSubject(userEmail); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장된다.
        Date now = new Date();

        //Access Token
        String accessToken = Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // set Expire Time
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 사용할 암호화 알고리즘과
                // signature 에 들어갈 secret값 세팅
                .compact();

        return accessToken;
    }

    // JWT 토큰에서 인증 정보 조회
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }

    // Request의 Header에서 token 값을 가져옵니다. "Authorization" : "TOKEN값'
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    // 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }
}