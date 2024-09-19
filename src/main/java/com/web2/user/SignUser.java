package com.web2.user;

public record SignUser(
        String nickname,
        String email,
        String password,
        String nationality,
        Boolean is_vegetarian,
        Integer age) {
}

