package com.chocchic.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.movie.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
