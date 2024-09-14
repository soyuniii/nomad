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
    private String address;

    /*private Double rating_sum; 리뷰 합계*/
    private Double latitude;
    private Double longitude;

    private Boolean is_vegetarian; // 채식 여부
    private Boolean is_gluten_free;
    private Boolean is_parking;
    private Boolean is_halal;

    private String opening_hours;
    private String close_day;

   /* @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE) //음식점 삭제되면 메뉴 삭제됨.
    private List<Menu> menuList = new ArrayList<>(); */

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviewList = new ArrayList<>();
}
