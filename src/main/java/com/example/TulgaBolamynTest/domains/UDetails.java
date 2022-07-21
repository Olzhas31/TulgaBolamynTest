package com.example.TulgaBolamynTest.domains;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class UDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String email;

    private String registratedDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    public UDetails(){}

    public UDetails(String name, String surname, String phoneNumber, String email, String registratedDate) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.registratedDate = registratedDate;
    }
}
