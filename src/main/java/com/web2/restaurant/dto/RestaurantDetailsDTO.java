package com.web2.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantDetailsDTO {
    private String name;
    private String category;
    private String address;
    private String weekdays;
    private String weekend;

    public RestaurantDetailsDTO(String name, String category, String address, String weekdays, String weekend) {
        this.name = name;
        this.category = category;
        this.address = address;
        this.weekdays = weekdays;
        this.weekend = weekend;
    }
}
