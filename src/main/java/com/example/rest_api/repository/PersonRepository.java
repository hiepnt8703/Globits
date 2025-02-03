package com.example.rest_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_api.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Page<Person> findAll(Pageable pageable);
}
