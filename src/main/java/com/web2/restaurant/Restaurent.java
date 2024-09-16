package com.web2.restaurant;

import com.web2.menu.Menu;
import com.web2.review.Review;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurent {

    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;
    private String name;

    private String city; // 추가
    private String address;

    // 카테고리1,2
    /*private Double rating_sum; 리뷰 합계*/
    private Double latitude;
    private Double longitude;

    private Boolean is_vegetarian; // 채식 여부
    private Boolean is_gluten_free;
    private Boolean is_free_parking; // is_parking에서 변경
    private Boolean is_halal;

    private String weekdays; // 추가
    private String weekend; // 추가

    // 평일 주말 운영시간 추가, 휴무일 삭제

   /* @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE) // 음식점 삭제되면 메뉴 삭제됨.
    private List<Menu> menuList = new ArrayList<>(); */

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();
}
