package com.web2.review.dto;

//해시태그 추가
public record ReviewUpdateRequest(String message, int rating, String hashtags) {
}
