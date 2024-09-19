package com.web2.restaurant;

import com.web2.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Restaurant {

    @Id
    @GeneratedValue
    @Column(name = "restaurant_id")
    private Long id;

    private String name;

    //카테고리 2,3 넣기?
    private String category; //ex) 베트남, 이탈리아 등등
    private String city; //추가
    //시, 구도 생각해보기

    /*private Double rating_sum; 리뷰 합계*/
    private Double latitude;
    private Double longitude;

    private String address;

    //평일,주말 운영시간으로 바꾸기
    private String weekdays;//추가
    private String weekend;//추가
    private Boolean is_free_parking;

    @Column(name = "is_vegetarian")
    private Boolean vegetarian;

    @Column(name = "is_halal")
    private Boolean halal;

    @Column(name = "is_gluten_free")
    private Boolean glutenfree;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    /*@OneToMany(mappedBy = "restaurant", cascade = CascadeType.REMOVE) //음식점 삭제되면 메뉴 삭제됨.
    private List<Menu> menuList = new ArrayList<>();*/

}
