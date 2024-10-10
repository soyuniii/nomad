package com.web2.restaurant.dto;

//위치 기반 검색에 이용
public record RestaurantDTO(Long id, String name, String category, String address, Double latitude, Double longitude, String weekdays, String weekend,
                            double averageRating, int reviewCount, String imageUrl) {
}

