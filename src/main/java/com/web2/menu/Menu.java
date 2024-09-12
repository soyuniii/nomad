package com.web2.menu;

import com.web2.restaurant.Restaurent;
import com.web2.review.Review;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "menu_id")
    private Long id;

    private String name; //있어야 될 것 같아요
    private String description;
    private Double price;
    /*private Double rating; 나중에 보자*/

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurent restaurant;

    @OneToMany
    private List<Review> reviews;

    //네이버 플레이스 API에서 제공하는 메뉴 ID를 저장하여 업데이트
    private String place_menu_id; //있어야 될 것 같아요..?
}
