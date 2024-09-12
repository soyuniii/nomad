package com.web2.answer;

import com.web2.BaseEntity;
import com.web2.review.Review;
import com.web2.user.User;
import com.web2.question.Question;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Answer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long id;

    private String content;

    //FK
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}