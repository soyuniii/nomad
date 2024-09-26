package com.web2.review;

import com.web2.restaurant.RestaurantRepository;
import com.web2.review.dto.ReviewDTO;
import com.web2.review.dto.ReviewResponseDTO;
import com.web2.review.dto.ReviewUpdateRequest;
import com.web2.global.SessionService;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final SessionService sessionService;
    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/reviews/new")
    public ResponseEntity<String> createReview(@RequestBody ReviewDTO reviewDTO,
                                               @RequestParam Long restaurantId,
                                               HttpSession session,
                                               @CookieValue(value = "SESSION_ID", required = false) String sessionId) {
        //SessionService 없을 때 원래 예외처리ㅌ   코드
        /*// 세션에서 저장된 사용자 정보 가져오기
        if (sessionId == null || !sessionId.equals(session.getId())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("잘못된 세션입니다. 다시 로그인해주세요.");
        }
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
        }*/
        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        Review review = new Review(reviewDTO.message(), reviewDTO.rating(),
                                   restaurantRepository.getReferenceById(restaurantId), user);
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
