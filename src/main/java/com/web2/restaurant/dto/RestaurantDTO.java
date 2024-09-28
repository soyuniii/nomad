package com.web2.restaurant.dto;

//위치 기반 검색에 이용
public record RestaurantDTO(String name, String category, String address, String weekdays, String weekend,
                            double averageRating, int reviewCount) {
}

