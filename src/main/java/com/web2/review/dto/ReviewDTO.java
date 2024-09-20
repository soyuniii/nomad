package com.web2.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private String nickName; //사용자 닉네임
    private String nationality; //사용자 국적
    private String message; //리뷰 메시지
    private int rating; //별점(1~5)
    private String createdAt; //생성 날짜

    public ReviewDTO(String nickName, String nationality, String message, int rating, String createdAt) {
        this.nickName = nickName;
        this.nationality = nationality;
        this.message = message;
        this.rating = rating;
        this.createdAt = createdAt;
    }
}
