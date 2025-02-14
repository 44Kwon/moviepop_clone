package com.teamproject.clonemoviepop.comment.service;

import com.teamproject.clonemoviepop.comment.dto.CommentDto;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.comment.repository.CommentRepository;
import com.teamproject.clonemoviepop.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Long createComment(Long reviewId, User user, CommentDto.Post postDto) {
        return Long.valueOf(3);
    }

    public Comment findComment(long commentId) {
        return findVerifiedCommentId(commentId);
    }

    private Comment findVerifiedCommentId(long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new IllegalArgumentException("나중에"));

        return comment;
    }
}
