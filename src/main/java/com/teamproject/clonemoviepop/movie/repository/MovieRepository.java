package com.teamproject.clonemoviepop.movie.repository;

import com.teamproject.clonemoviepop.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContains(String title);

    List<Movie> findByAdultedIsFalseAndTitleContains(String query);
}
