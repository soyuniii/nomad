package com.web2.restaurant.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class RestaurantDTO {
    private final String name;
    private final String category;
    private final String address;
    private final String weekdays;
    private final String weekend;
    private final double averageRating;
    private final int reviewCount;
}

