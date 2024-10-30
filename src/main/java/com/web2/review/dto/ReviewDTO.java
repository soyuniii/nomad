package com.web2.review.dto;

//음식점 세부 정보 조회 -> 리뷰 조회
public record ReviewDTO(String nickName, String nationality, String message, int rating, String createdAt, String updateAt, String hashtags,String ImageUrl) {
}
