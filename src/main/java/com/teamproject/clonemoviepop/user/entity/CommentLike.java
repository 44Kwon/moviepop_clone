package com.teamproject.clonemoviepop.user.entity;

import com.teamproject.clonemoviepop.comment.entity.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Getter
@NoArgsConstructor
@Entity
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commentLikeId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        CommentLike that = (CommentLike) object;
        return commentLikeId == that.commentLikeId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commentLikeId);
    }
}
