package com.example.TulgaBolamynTest.repositories;

import com.example.TulgaBolamynTest.domains.UDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UDetailsRepository extends JpaRepository<UDetails, Long> {
}
