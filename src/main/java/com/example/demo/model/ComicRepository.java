package com.example.demo.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComicRepository extends JpaRepository<Comic, Integer> {

    boolean existsByNum(int num);

    Optional<Comic> findByNum(int num);

}
