package com.web2.Friends;

import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Builder
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;
    private String nationality;

    // 1:n 유저와 친구
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Friends(){}

}
