package com.web2.review;

import com.web2.global.SessionService;
import com.web2.restaurant.RestaurantRepository;
import com.web2.review.dto.ReviewDTO;
import com.web2.review.dto.ReviewResponseDTO;
import com.web2.review.dto.ReviewUpdateRequest;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final SessionService sessionService;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/reviews/new")
    @Transactional
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO,
                                               @RequestParam Long restaurantId,
                                               @CookieValue(value = "SESSION_ID", required = false) String sessionId,
                                               HttpSession session) {
        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        Review review = new Review(reviewDTO.message(), reviewDTO.rating(),
                restaurantRepository.getReferenceById(restaurantId), user, reviewDTO.hashtags());
        reviewRepository.save(review);

        return ResponseEntity.ok("리뷰가 작성되었습니다.");
    }

    //restaurantId로 조회
    @GetMapping("/restaurants/{id}/reviews")
    public ResponseEntity<ReviewResponseDTO> getReviews(@PathVariable Long id) {
        ReviewResponseDTO reviewResponseDTOS = reviewService.getReviewList(id);
        return ResponseEntity.ok(reviewResponseDTOS);
    }

    @PatchMapping("/reviews/update/{id}")
    public ResponseEntity<String> updateReview(@PathVariable Long id,
                                               @RequestBody ReviewUpdateRequest request,
                                               HttpSession session,
                                               @CookieValue(value = "SESSION_ID", required = false) String sessionId) {

        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);

        reviewService.updateReview(request, id);

        return ResponseEntity.ok("리뷰가 수정되었습니다.");
    }

    @DeleteMapping("/reviews/delete/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable Long id,
                                               HttpSession session,
                                               @CookieValue(value = "SESSION_ID", required = false) String sessionId) {
        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);

        reviewRepository.deleteById(id);

        return ResponseEntity.ok("리뷰가 삭제되었습니다.");
    }

}
