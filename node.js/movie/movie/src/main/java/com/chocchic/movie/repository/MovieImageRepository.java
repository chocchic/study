package com.chocchic.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chocchic.movie.model.MovieImage;

public interface MovieImageRepository extends JpaRepository<MovieImage, Long> {

}
