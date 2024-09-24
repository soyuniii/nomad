package com.web2.review;

import com.web2.restaurant.Restaurant;
import com.web2.restaurant.RestaurantRepository;
import com.web2.restaurant.RestaurantService;
import com.web2.review.dto.ReviewDTO;
import com.web2.review.dto.ReviewResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantService restaurantService;

    public ReviewResponseDTO getReviewList(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));

        List<Review> reviews = restaurant.getReviews();

        List<ReviewDTO> reviewDTOS = restaurant.getReviews().stream()
                .map(review -> {
                    String nickname = review.getUser().getNickname();
                    String nationality = review.getUser().getNationality();
                    double averageRating = restaurantService.calculateAverageRating(restaurant.getReviews());
                    int reviewCount = restaurant.getReviews().size();

                    String createdAt = review.getCreatedAt().format(formatter); // 포맷 적용

                    return new ReviewDTO(
                            nickname,
                            nationality,
                            review.getMessage(),
                            review.getRating(),
                            createdAt
                    );
                }).collect(Collectors.toList());

        double averageRating = restaurantService.calculateAverageRating(restaurant.getReviews());
        int reviewCount = reviews.size();

        return new ReviewResponseDTO(reviewDTOS, averageRating, reviewCount);
    }
}
