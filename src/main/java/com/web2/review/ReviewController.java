package com.web2.review;

import com.web2.restaurant.RestaurantRepository;
import com.web2.review.dto.ReviewDTO;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/reviews/new")
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO,
                                               @RequestParam Long restaurantId,
                                               HttpSession session) {
        //session에서 사용자 정보 가져오기
        String csrfToken = (String) session.getAttribute("csrfToken");
        //토큰 여부 확인
        if (csrfToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("사용자 인증이 필요합니다.");
        }
        //세션에 저장된 사용자 정보 확인
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 후 이용해주세요.");
        }

        Review review = new Review(reviewDTO.getMessage(), reviewDTO.getRating(),
                                   restaurantRepository.getReferenceById(restaurantId), user);
        reviewRepository.save(review);

        return ResponseEntity.ok("리뷰가 작성되었습니다.");
    }

    //restaurantId로 조회
    @GetMapping("/restaurants/{id}/reviews")
    public ResponseEntity<List<ReviewDTO>> getReviews(@PathVariable Long id) {
        List<ReviewDTO> reviewDTOS = reviewService.getReviewList(id);
        return ResponseEntity.ok(reviewDTOS);
    }
}
