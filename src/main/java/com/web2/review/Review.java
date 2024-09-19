package com.web2.review;

import com.web2.BaseEntity;
import com.web2.restaurant.Restaurant;
import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String message;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

/*    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //자기 자신 참조하는 속성 추가하기
}
