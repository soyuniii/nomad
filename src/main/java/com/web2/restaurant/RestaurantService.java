package com.web2.restaurant;

import com.web2.restaurant.dto.RestaurantDTO;
import com.web2.restaurant.dto.RestaurantDetailsDTO;
import com.web2.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findRestaurantNearLocation(double latitude, double longitude, double radius) {
        // Haversine formula를 사용하여 근접한 음식점 검색
        List<Restaurant> restaurants = restaurantRepository.findNearbyRestaurants(latitude, longitude, radius);

        return restaurants.stream()
                .map(restaurant -> {
                    double averageRating = calculateAverageRating(restaurant.getReviews());
                    int reviewCount = restaurant.getReviews().size();

                    return new RestaurantDTO(
                            restaurant.getName(),
                            restaurant.getCategory(),
                            restaurant.getAddress(),
                            restaurant.getWeekdays(),
                            restaurant.getWeekend(),
                            averageRating,
                            reviewCount
                    );
                }).collect(Collectors.toList());
    }

    //마커로 띄우고 누르면 조회
    //Restaurantid 기반으로 음식점 자세히 조회
    public RestaurantDetailsDTO getRestaurantDetails(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("음식점을 찾을 수 없습니다."));

        return new RestaurantDetailsDTO(
                restaurant.getName(),
                restaurant.getCategory(),
                restaurant.getAddress(),
                restaurant.getWeekdays(),
                restaurant.getWeekend(),
                convertBooleanToString(restaurant.getVegetarian()),
                convertBooleanToString(restaurant.getGlutenfree()),
                convertBooleanToString(restaurant.getHalal())
        );
    }

    private String convertBooleanToString(Boolean value) {
        return value ? "있음" : "없음";
    }

    private Double calculateAverageRating(List<Review> reviews) {
        double average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);
        //소수 첫째자리까지 반올림
        return Math.round(average * 10.0) / 10.0;
    }

    public void saveAll(List<Restaurant> restaurants) {
        restaurantRepository.saveAll(restaurants);
    }
}
