package com.web2.restaurant.dto;

import com.web2.review.dto.ReviewDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RestaurantDTO {
    private Long id;
    private String name;
    private String category;
    private String address;
    private int rating;
    private List<ReviewDTO> reviews;

    public RestaurantDTO(Long id, String name, String category, String address, Double aDouble, List<ReviewDTO> reviewDTOS) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.address = address;
        this.rating = getRating();
        this.reviews = reviewDTOS;
    }
}

