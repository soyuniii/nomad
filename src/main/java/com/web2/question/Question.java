package com.web2.question;

import com.web2.BaseEntity;
import com.web2.answer.Answer;
import com.web2.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    private String title;
    private String content;

    //FK
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "question")
    private List<Answer> answerList;

}

// 뒤끝을 통한 실시간 채팅 서비스를 구현할 것임. 그렇다면.. 기존에 존