package com.web2.restaurant;

import com.web2.global.google.GooglePlacesService;
import com.web2.global.s3.S3Service;
import com.web2.restaurant.dto.RestaurantDTO;
import com.web2.restaurant.dto.RestaurantDetailsDTO;
import com.web2.review.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final GooglePlacesService googlePlacesService;
    private final S3Service s3Service;

    public List<RestaurantDTO> findRestaurantNearLocation(double latitude, double longitude, double radius, String userNationality) {
        // Haversine formula를 사용하여 근접한 음식점 검색
        List<Restaurant> restaurants = restaurantRepository.findNearbyRestaurantsByCategory(latitude, longitude, radius, userNationality);

        return restaurants.stream()
                .map(restaurant -> {
                    double averageRating = calculateAverageRating(restaurant.getReviews());
                    int reviewCount = restaurant.getReviews().size();

                    // Google Places API로 사진 가져오기
                    String photoReference = googlePlacesService.getPhotoReference(restaurant.getName(), restaurant.getAddress());
                    String photoUrl = photoReference != null ? googlePlacesService.getPhotoUrl(photoReference) : null;

                    // S3에 사진 업로드
                    String s3ImageUrl = null;
                    try {
                        s3ImageUrl = photoUrl != null ? s3Service.uploadFileFromUrl(photoUrl) : null;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return new RestaurantDTO(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getCategory(),
                            restaurant.getAddress(),
                            restaurant.getLatitude(),
                            restaurant.getLongitude(),
                            restaurant.getWeekdays(),
                            restaurant.getWeekend(),
                            averageRating,
                            reviewCount,
                            s3ImageUrl
                    );
                }).collect(Collectors.toList());
    }

    //검색 키워드 기반으로 RestaurantDTO 반환
    public List<RestaurantDTO> searchRestaurant(String keyword) {
        String cleanKeyword = keyword.trim(); // 공백 제거
        List<Restaurant> restaurants = restaurantRepository.findRestaurantsByReviewHashtags(cleanKeyword);

        return restaurants.stream()
                .map(restaurant -> {
                    double averageRating = calculateAverageRating(restaurant.getReviews());
                    int reviewCount = restaurant.getReviews().size();

                    //검색할 때도 사진 추가해야 됨
                    String photoReference = googlePlacesService.getPhotoReference(restaurant.getName(),restaurant.getAddress());
                    String photoUrl = photoReference != null ? googlePlacesService.getPhotoUrl(photoReference) : null;

                    String imageUrl = null; // S3에 업로드하고 URL 반환
                    try {
                        imageUrl = photoUrl != null ? s3Service.uploadFileFromUrl(photoUrl) : null;
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return new RestaurantDTO(
                            restaurant.getId(),
                            restaurant.getName(),
                            restaurant.getCategory(),
                            restaurant.getAddress(),
                            restaurant.getLatitude(),
                            restaurant.getLongitude(),
                            restaurant.getWeekdays(),
                            restaurant.getWeekend(),
                            averageRating,
                            reviewCount,
                            imageUrl
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

    public Double calculateAverageRating(List<Review> reviews) {
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
