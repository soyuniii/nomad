package com.web2.restaurant;

import com.web2.global.SessionService;
import com.web2.restaurant.dto.LocationRequest;
import com.web2.restaurant.dto.RestaurantDTO;
import com.web2.restaurant.dto.RestaurantDetailsDTO;
import com.web2.user.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final SessionService sessionService;
    private final RestaurantRepository restaurantRepository;

    //React native에서 JSON으로 사용자 위치 데이터(위도, 경도, 반경)를 전송해서 RequestBody로 받아 사용
    //반경 내의 음식점 리스트 다시 프론트로 반환 -> 네이버 지도 뷰에서 마커로 띄움.
    @PostMapping("/search/location")
    public List<RestaurantDTO> searchRestaurants(@RequestBody LocationRequest request,
                                                 HttpSession session,
                                                 @CookieValue(value = "SESSION_ID", required = false) String sessionId) {

        if (sessionId == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "No Session ID");
        }

        sessionService.validateSession(sessionId, session);
        sessionService.validateCsrfToken(session);
        User user = sessionService.validateUser(session);

        double latitude = request.getLatitude();
        double longitude = request.getLongitude();
        double radius = request.getRadius();

        String userNationality = user.getNationality();

        return restaurantService.findRestaurantNearLocation(latitude, longitude, radius, userNationality);
    }

    @GetMapping("/search")
    public ResponseEntity<List<RestaurantDTO>> searchByHashtag(@RequestParam String keyword) {
        List<RestaurantDTO> restaurantDTOS = restaurantService.searchRestaurant(keyword);
        return ResponseEntity.ok(restaurantDTOS);
    }

    //음식저 세부정보 조회(리뷰는 개수만)
    @GetMapping("/{id}")
    public RestaurantDetailsDTO getRestaurantDetails(@PathVariable("id") Long id) {
        RestaurantDetailsDTO restaurantDetailsDTO = restaurantService.getRestaurantDetails(id);
        return restaurantDetailsDTO;
    }

   /* //카테고리가 베트남인 식당 필터링 -> 1769개
    @GetMapping("/category-vietnam")
    public List<Restaurant> getVietnamRestaurants() {
        return restaurantRepository.findByCategory("베트남");
    }

    //필터링
    @GetMapping("/vegetarian-restaurants")
    public List<Restaurant> getRestaurantFilterIsvegetarian() {
        return restaurantRepository.findByVegetarian(true);
    }

    @GetMapping("/gluten-free-restaurants")
    public List<Restaurant> getRestaurantFilterIsGlutenFree() {
        return restaurantRepository.findByGlutenfree(true);
    }

    @GetMapping("/halal-restaurants")
    public List<Restaurant> getRestaurantFilterIsHalal() {
        return restaurantRepository.findByHalal(true);
    }*/
}
