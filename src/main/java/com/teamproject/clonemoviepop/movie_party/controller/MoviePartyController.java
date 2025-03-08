package com.teamproject.clonemoviepop.movie_party.controller;

import com.teamproject.clonemoviepop.movie_party.service.MoviePartyService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/groups")
@Validated
public class MoviePartyController {
    public static final String MOVIE_PARTY_DEFAULT_URI = "/groups";
//    private final MoviePartyService moviePartyService;
}
