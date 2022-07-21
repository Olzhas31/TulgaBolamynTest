package com.example.TulgaBolamynTest.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer point20;

    private String date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
