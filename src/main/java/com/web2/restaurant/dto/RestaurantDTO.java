package com.web2.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDTO {
    private String name;
    private String category;
    private String address;
    private String weekdays;
    private String weekend;
    private double averageRating;
    private int reviewCount;

    public RestaurantDTO(String name, String category, String address, String weekdays, String weekend, double averageRating, int reviewCount) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.weekdays = weekdays;
        this.weekend = weekend;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }
}

