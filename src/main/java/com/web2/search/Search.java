package com.web2.search;

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
public class Search {

    @Id
    @GeneratedValue
    @Column(name = "search_id")
    private Long id;

    private String search_keyword;
    private Double latitude;
    private Double longitude;
}
