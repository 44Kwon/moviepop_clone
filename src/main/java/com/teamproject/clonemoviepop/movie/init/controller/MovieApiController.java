package com.teamproject.clonemoviepop.movie.init.controller;

import com.teamproject.clonemoviepop.movie.init.service.MovieApiService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MovieApiController {
    private final MovieApiService movieApiService;

    public MovieApiController(MovieApiService movieApiService) {
        this.movieApiService = movieApiService;
    }

    //클라이언트쪽에서 강제갱신할때 필요
    @Scheduled(cron = "00 12 23 6 * ?", zone = "Asia/Seoul")
    public void makeInitData() {

    }
}
