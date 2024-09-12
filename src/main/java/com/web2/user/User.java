package com.web2.foreign_member;

import com.web2.question.Question;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Foreign_Member {

    @Id
    @GeneratedValue
    @Column(name = "foreign_member_id") //실제 DB에서는 foreign_member_id라고 한다는 뜻
    private Long id;

    private String email;
    private String password;

    private String name;
    private String nationality;
    private Boolean is_vegetarian;
    private Integer age;

}
