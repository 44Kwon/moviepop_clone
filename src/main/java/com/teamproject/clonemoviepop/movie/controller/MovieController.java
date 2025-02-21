package com.teamproject.clonemoviepop.movie.controller;

import com.teamproject.clonemoviepop.dto.ResponseDto;
import com.teamproject.clonemoviepop.movie.dto.MovieDto;
import com.teamproject.clonemoviepop.movie.mapper.MovieMapper;
import com.teamproject.clonemoviepop.movie.service.MovieService;
import com.teamproject.clonemoviepop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/movies")
@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public ResponseEntity searchMovies(String q, User user) {
        List<MovieDto.Response> responses = movieService.searchMovies(user, q);

        return new ResponseEntity(new ResponseDto.SingleResponseDto<>(responses),
                HttpStatus.OK);
    }
}

