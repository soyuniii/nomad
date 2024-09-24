package com.web2.review.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReviewDTO {
    private final String nickName; //사용자 닉네임
    private final String nationality; //사용자 국적
    private final String message; //리뷰 메시지
    private final int rating; //별점(1~5)
    private final String createdAt; //생성 날짜
}
