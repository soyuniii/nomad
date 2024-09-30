package com.web2.review.dto;

//해시태그 추가
public record ReviewUpdateDTO(String message, int rating, String hashtags) {
}
