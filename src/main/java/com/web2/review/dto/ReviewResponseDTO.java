package com.web2.review.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ReviewResponseDTO {
    private final List<ReviewDTO> reviews;
    private final double averageRating;
    private final int reviewCount;
}
