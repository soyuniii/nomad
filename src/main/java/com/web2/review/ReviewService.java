package com.web2.review;

import com.web2.restaurant.Restaurant;
import com.web2.restaurant.RestaurantRepository;
import com.web2.restaurant.RestaurantService;
import com.web2.review.dto.ReviewDTO;
import com.web2.review.dto.ReviewResponseDTO;
import com.web2.review.dto.ReviewUpdateRequest;
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
    private final ReviewRepository reviewRepository;

    public ReviewResponseDTO getReviewList(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm");

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));

        List<Review> reviews = restaurant.getReviews();

        List<ReviewDTO> reviewDTOS = restaurant.getReviews().stream()
                .map(review -> {
                    String nickname = review.getUser().getNickname();
                    String nationality = review.getUser().getNationality();
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

    //리뷰 수정 메서드
    public void updateReview(ReviewUpdateRequest request,
                             Long id) { //review_id
        String updatemessage = request.message();
        int updaterating = request.rating();

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을수 없습니다."));

        review.setMessage(updatemessage);
        review.setRating(updaterating);

        reviewRepository.save(review);
    }
}
