package com.web2.restaurant;

import com.web2.restaurant.dto.RestaurantDTO;
import com.web2.review.Review;
import com.web2.review.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findRestaurantNearLocation(double latitude, double longitude, double radius) {
        // Haversine formula를 사용하여 근접한 음식점 검색
        return restaurantRepository.findNearbyRestaurants(latitude, longitude, radius);
    }

    //마커로 띄우고 누르면 조회
    //Restaurantid 기반으로 음식점 자세히 조회
    public RestaurantDTO getRestaurantList(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));

        List<ReviewDTO> reviewDTOS = restaurant.getReviews().stream()
                .map(review -> new ReviewDTO(review.getUser().getNickname(), review.getUser().getNationality(),
                                             review.getMessage(), review.getRating()))
                .collect(Collectors.toList());

        return new RestaurantDTO(
                restaurant.getId(),
                restaurant.getName(),
                restaurant.getCategory(),
                restaurant.getAddress(),
                calculateAverageRating(restaurant.getReviews()),
                reviewDTOS
        );
    }

    private Double calculateAverageRating(List<Review> reviews) {
        double average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
        //소수 첫째자리까지 반올림
        return Math.round(average * 10.0) / 10.0;
    }


    //사용자가 마커를 클릭하면 세부 정보 조회 가능(카테고리는 이미 위치 기반 음식점에서 필터링?되있음.
    //그래도 뷰에 카테고리 같이 띄우기
    /*public List<Restaurant> getRestaurantList(Long id){
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));

    }*/

    public void saveAll(List<Restaurant> restaurents) {
        restaurantRepository.saveAll(restaurents);
    }
}
