package com.web2.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id") //실제 DB에서는 user_id 라고 한다는 뜻
    private Long id;

    private String email;
    private String password;

    private String nickname;
    private String nationality;
    private Boolean is_vegetarian;
    private Integer age;

     /*private Double longitude;
     private Double latitude;
     검색 시 위치를 받아오는 것이 더 안정적이라 판단됨*/
}
