package com.web2.Friends;

import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Friends {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friends_id")
    private Integer id;  // 변수명을 소문자로 변경하여 Java 표기 규칙을 따름

    private String nickname;
    private String nationality;

    // 유저와의 관계 설정
    @ManyToOne(fetch = FetchType.LAZY)  // FetchType.LAZY를 사용하여 필요할 때만 데이터를 로드
    @JoinColumn(name = "user_id")  // 외래 키 설정
    private User user;

    public Friends(String nickname, String nationality, User user) {
        this.nickname = nickname;
        this.nationality = nationality;
        this.user = user; // 친구의 유저 설정
    }


}
