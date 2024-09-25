package com.web2.review.dto;

import java.util.List;

public record ReviewResponseDTO(List<ReviewDTO> reviews, double averageRating, int reviewCount) {
}
