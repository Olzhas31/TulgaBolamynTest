package com.example.TulgaBolamynTest.repositories;

import com.example.TulgaBolamynTest.domains.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
