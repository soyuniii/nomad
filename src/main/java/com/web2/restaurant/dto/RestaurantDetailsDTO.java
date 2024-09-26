package com.web2.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//마커를 눌렀을 때 음식점에 대해 자세히 조회(리뷰는 따로 조회)
public class RestaurantDetailsDTO {
    private String name;
    private String category;
    private String address;
    private String weekdays;
    private String weekend;
    //아래 속성들만 추가
    private String vegetarian;
    private String glutenfree;
    private String halal;

    public RestaurantDetailsDTO(String name, String category, String address, String weekdays, String weekend, String vegetarian, String glutenfree, String halal) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.weekdays = weekdays;
        this.weekend = weekend;
        this.vegetarian = vegetarian;
        this.glutenfree = glutenfree;
        this.halal = halal;
    }
}
