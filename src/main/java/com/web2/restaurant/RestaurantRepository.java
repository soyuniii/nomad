package com.web2.restaurant;

import com.web2.restaurant.dto.RestaurantDTO;
import jdk.jfr.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByVegetarian(Boolean vegetarian);

    List<Restaurant> findByHalal(Boolean halal);

    List<Restaurant> findByGlutenfree(Boolean GlutenFree);

    List<Restaurant> findByCategory(String category);

    //@Query 어노테이션을 통해 쿼리가 실행될 때 MySQL이 해당 쿼리를 처리
    @Query(value = "SELECT *, "
            + "(6371 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) AS distance "
            + "FROM restaurant r "
            + "HAVING distance < :radius "
            + "ORDER BY distance", nativeQuery = true)
    List<Restaurant> findNearbyRestaurants(@Param("latitude") double latitude,
                                           @Param("longitude") double longitude,
                                           @Param("radius") double radius);

    //해시태그가 포함된 리뷰가 있는 음식점을 문자열 파싱해서 검색
    //distinct -> 리뷰에 해당 해시태그가 여러 번 포함되어 있더라도 중복된 음식점이 하나만 반환
    @Query("SELECT DISTINCT r FROM Restaurant r JOIN r.reviews rev WHERE rev.hashtags LIKE %:keyword%")
    List<Restaurant> findRestaurantsByReviewHashtags(@Param("keyword") String keyword);

}
