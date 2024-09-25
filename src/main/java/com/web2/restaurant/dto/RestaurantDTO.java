package com.web2.restaurant.dto;

public record RestaurantDTO(String name, String category, String address, String weekdays, String weekend,
                            double averageRating, int reviewCount) {
}

