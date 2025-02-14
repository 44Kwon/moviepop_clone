package com.teamproject.clonemoviepop.comment.controller;

import com.teamproject.clonemoviepop.comment.dto.CommentDto;
import com.teamproject.clonemoviepop.comment.mapper.CommentMapper;
import com.teamproject.clonemoviepop.comment.service.CommentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviewBoards/{review-id}/comments")
@Validated
public class CommentController {
    private static final String COMMENT_DEFAULT_URL = "/comments";
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@PathVariable("review-id") @Positive long reviewId,
                                      @RequestBody @Valid CommentDto.Post requestBody) {

        return ResponseEntity.ok("123");
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("review-id") @Positive long reviewId,
                                       @PathVariable("comment-id") @Positive long commentId,
                                       @RequestBody @Valid CommentDto.Patch requestBody) {

        return ResponseEntity.ok("123");
    }

    //나중에처리
    @GetMapping("/{comment-id")
    public ResponseEntity getComment(@PathVariable("review-id") @Positive long reviewId,
                                     @PathVariable("comment-id") @Positive long commentId) {

        return ResponseEntity.ok("123");
    }

    @GetMapping
    public ResponseEntity getComments(@PathVariable("review-id") @Positive long reviewId,
                                      @RequestParam @Positive int page, //@RequestParam 파라미터 지정안하면 변수이름 사용
                                      @RequestParam @Positive int size) {

        return ResponseEntity.ok("123");
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId) {

        return ResponseEntity.ok("123");
    }
}
