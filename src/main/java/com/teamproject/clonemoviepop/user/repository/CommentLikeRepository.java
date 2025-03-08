package com.teamproject.clonemoviepop.user.repository;

import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.user.entity.CommentLike;
import com.teamproject.clonemoviepop.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    CommentLike findByCommentAndUser(Comment comment, User user);
    boolean existsByCommentAndUser(Comment comment, User user);
    void deleteByComment(Comment comment);
}
