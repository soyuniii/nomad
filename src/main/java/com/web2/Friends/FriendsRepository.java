package com.web2.Friends;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends, Integer> {


    @Query(value = "SELECT *, " +
            "(6371 * acos(cos(radians(:latitude)) * cos(radians(f.latitude)) * cos(radians(f.longitude) - radians(:longitude)) + " +
            "sin(radians(:latitude)) * sin(radians(f.latitude)))) AS distance " +
            "FROM friends f " +
            "HAVING distance < :radius " +
            "ORDER BY distance", nativeQuery = true)
    List<Friends> findNearbyFriends(@Param("latitude") double latitude,
                                    @Param("longitude") double longitude,
                                    @Param("radius") double radius);
}



// Service 에서 상용
/*
//@Query 어노테이션을 통해 쿼리가 실행될 때 MySQL이 해당 쿼리를 처리
@Query(value = "SELECT *, "
        + "(6371 * acos(cos(radians(:latitude)) * cos(radians(r.latitude)) * cos(radians(r.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(r.latitude)))) AS distance "
        + "FROM restaurant r "
        + "HAVING distance < :radius "
        + "ORDER BY distance", nativeQuery = true)
List<Restaurant> findNearbyRestaurants(@Param("latitude") double latitude,
                                       @Param("longitude") double longitude,
                                       @Param("radius") double radius);*/