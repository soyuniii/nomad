package com.web2.restaurant;

import com.web2.restaurant.dto.LocationRequest;
import com.web2.restaurant.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @PostMapping("/search")
    //React native에서 JSON으로 사용자 위치 데이터(위도, 경도, 반경)를 전송해서 RequesstBody로 받아 사용
    //반경 내의 음식점 리스트 다시 프론트로 반환 -> 네이버 지도 뷰에서 마커로 띄움.
    public List<Restaurant> searchRestaurants(@RequestBody LocationRequest request) {
        //search가 아니라 recommend?라고 해야할 듯
        double latitude = request.getLatitude();
        double longitude = request.getLongitude();
        double radius = request.getRadius();

        //한국 밖에서 위도 경도 미지원 기능 예외 추가
        return restaurantService.findRestaurantNearLocation(latitude, longitude, radius);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDTO> getRestaurantDetails(@PathVariable("id") Long id) {
        RestaurantDTO restaurantDTO = restaurantService.getRestaurantList(id);
        return ResponseEntity.ok(restaurantDTO);
    }

    //카테고리가 베트남인 식당 필터링 -> 1769개
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
    public List<Restaurant> getRestaurantFilterGlutenFree() {
        return restaurantRepository.findByGlutenfree(true);
    }

    @GetMapping("/halal-restaurants")
    public List<Restaurant> getRestaurantFilterHalal() {
        return restaurantRepository.findByHalal(true);
    }
}
