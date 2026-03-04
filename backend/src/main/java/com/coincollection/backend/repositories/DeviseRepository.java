package com.coincollection.backend.repositories;

import com.coincollection.backend.models.Devise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviseRepository extends JpaRepository<Devise, Long> {
    
}