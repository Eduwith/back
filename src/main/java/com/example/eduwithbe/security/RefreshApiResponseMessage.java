package com.example.eduwithbe.security;

import java.util.Map;

public class RefreshApiResponseMessage {
    private Map<String,String> s;

    public RefreshApiResponseMessage(Map<String, String> map) {
        this.s = map;
    }
}
