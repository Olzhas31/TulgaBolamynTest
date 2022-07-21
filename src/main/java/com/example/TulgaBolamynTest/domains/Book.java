package com.example.TulgaBolamynTest.domains;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String author;

    private Boolean hidden = true;

    @OneToMany(mappedBy = "book")
    private Set<Question> questions;

    public Book(){}

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", hidden=" + hidden +
                '}';
    }
}
