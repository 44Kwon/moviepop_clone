package com.teamproject.clonemoviepop.comment.controller;

import com.teamproject.clonemoviepop.comment.dto.CommentDto;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.comment.mapper.CommentMapper;
import com.teamproject.clonemoviepop.comment.service.CommentService;
import com.teamproject.clonemoviepop.dto.ResponseDto;
import com.teamproject.clonemoviepop.user.entity.User;
import com.teamproject.clonemoviepop.utils.UriComponent;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/reviewBoards/{review-id}/comments")
@Validated
public class CommentController {
    private static final String COMMENT_DEFAULT_URL = "/reviewBoards/{review-id}/comments";
    private final CommentService commentService;
    private final CommentMapper mapper;

    public CommentController(CommentService commentService, CommentMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postComment(@PathVariable("review-id") @Positive long reviewId,
                                      @RequestBody @Valid CommentDto.Post requestBody, User user) {
        Long commentId = commentService.createComment(reviewId, user, requestBody);
        URI location = UriComponent.createCommentUri(COMMENT_DEFAULT_URL, reviewId, commentId);

        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{comment-id}")
    public ResponseEntity patchComment(@PathVariable("review-id") @Positive long reviewId,
                                       @PathVariable("comment-id") @Positive long commentId,
                                       @RequestBody @Valid CommentDto.Patch requestBody, User user) {
        //해당 리뷰글에 속한 댓글이 맞는지 검증로직 필요성
        requestBody.setCommentId(commentId);
        CommentDto.CommentUpdateResponse commentUpdateResponse = commentService.updateComment(user, requestBody);

        return new ResponseEntity(new ResponseDto.SingleResponseDto<>(commentUpdateResponse), HttpStatus.OK);
    }

    //나중에처리
    @GetMapping("/{comment-id")
    public ResponseEntity getComment(@PathVariable("review-id") @Positive long reviewId,
                                     @PathVariable("comment-id") @Positive long commentId) {
        //해당 리뷰글에 속한 댓글이 맞는지 검증로직 필요성
        CommentDto.Response commentResponse = commentService.findComment(commentId);
        return new ResponseEntity(new ResponseDto.SingleResponseDto<>(commentResponse), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getComments(@PathVariable("review-id") @Positive long reviewId,
                                      @RequestParam @Positive int page, //@RequestParam 파라미터 지정안하면 변수이름 사용
                                      @RequestParam @Positive int size) {
        //해당 리뷰글에 속한 댓글이 맞는지 검증로직 필요성
        ResponseDto.MultipleResponseDto<Comment> comments = commentService.findComments(page, size);
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    @DeleteMapping("/{comment-id}")
    public ResponseEntity deleteComment(@PathVariable("comment-id") @Positive long commentId, User user) {
        commentService.deleteComment(user, commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
