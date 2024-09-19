package com.web2.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private String nickName; //사용자 닉네임
    private String nationality; //사용자 국적
    private String message; //리뷰 메시지
    private Double rating; //별점

    public ReviewDTO(String nickName, String nationality, String message, Double rating) {
        this.nickName = nickName;
        this.nationality = nationality;
        this.message = message;
        this.rating = Double.valueOf(rating);
    }
}
