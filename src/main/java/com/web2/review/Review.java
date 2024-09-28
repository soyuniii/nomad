package com.web2.review;

import com.web2.BaseEntity;
import com.web2.restaurant.Restaurant;
import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    private String message;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String hashtags; //추가


   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "review_hashtag",
            joinColumns = @JoinColumn(name = "review_id"),
            //연관된 엔티티(Hashtag)의 외래 키를 설정
            inverseJoinColumns = @JoinColumn(name = "hashtag_id")
    )
    private Set<Hashtag> hashtags = new HashSet<>();
*/
    public Review(String message, int rating, Restaurant restaurant, User user, String hashtags) {
        this.message = message;
        this.rating = rating;
        this.restaurant = restaurant;
        this.user = user;
        this.hashtags = hashtags;
    }
}
