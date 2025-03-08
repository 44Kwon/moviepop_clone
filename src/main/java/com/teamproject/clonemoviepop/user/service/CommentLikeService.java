package com.teamproject.clonemoviepop.user.service;

import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.user.entity.CommentLike;
import com.teamproject.clonemoviepop.user.entity.User;
import com.teamproject.clonemoviepop.user.repository.CommentLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;

    public CommentLike createCommentLike(CommentLike commentLike) {
        return commentLikeRepository.save(commentLike);
    }

    public CommentLike findByCommentAndUser(Comment comment, User user) {
        return commentLikeRepository.findByCommentAndUser(comment, user);
    }

    boolean existsByCommentAndUser(Comment comment, User user) {
        return commentLikeRepository.existsByCommentAndUser(comment,user);
    }
}
