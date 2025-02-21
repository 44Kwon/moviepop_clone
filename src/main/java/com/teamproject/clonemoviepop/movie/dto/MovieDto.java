package com.teamproject.clonemoviepop.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class MovieDto {
    @Getter
    @AllArgsConstructor
    public static class Response {
        private Long movieId;
        private String title;
    }
}
