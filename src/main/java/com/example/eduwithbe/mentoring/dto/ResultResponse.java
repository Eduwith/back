package com.example.eduwithbe.mentoring.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResultResponse {
    private String result;

    public ResultResponse() {
        this.result = "SUCCESS";
    }
}
