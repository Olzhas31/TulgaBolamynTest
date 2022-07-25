package com.example.TulgaBolamynTest.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String quest;

    private String option0;
    private String option1;
    private String option2;
    private String option3;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    public Question(){}

    public Question(Book book) {
        this.book = book;
    }
}
