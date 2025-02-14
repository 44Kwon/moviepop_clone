package com.teamproject.clonemoviepop.review_board.controller;

import com.teamproject.clonemoviepop.review_board.mapper.ReviewBoardMapper;
import com.teamproject.clonemoviepop.review_board.service.ReviewBoardService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/reviewBoards")
@RestController
@Validated
public class ReviewBoardController {

    private final static String REVIEW_BOARD_DEFAULT_URI = "/reviewBoards";
    private final ReviewBoardService reviewBoardService;
    private final ReviewBoardMapper reviewBoardMapper;

    public ReviewBoardController(ReviewBoardService reviewBoardService, ReviewBoardMapper reviewBoardMapper) {
        this.reviewBoardService = reviewBoardService;
        this.reviewBoardMapper = reviewBoardMapper;
    }

//    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity
}
