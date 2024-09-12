package com.web2.review;

import com.web2.BaseEntity;
import com.web2.restaurant.Restaurent;
import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String message;
    private Double rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurent restaurant;

/*    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;*/

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
