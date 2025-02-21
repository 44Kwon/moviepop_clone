package com.teamproject.clonemoviepop.comment.entity;

import com.teamproject.clonemoviepop.audit.Auditable;
import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.user.entity.CommentLike;
import com.teamproject.clonemoviepop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@Getter
@Table(name = "comments")
public class Comment extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false)
    private Integer likes = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REVIEW_BOARD_ID")
    private ReviewBoard reviewBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    @Setter
    private User user;

    @OneToMany(mappedBy = "comment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentLike> commentLikes = new HashSet<>();

    //댓글 작성 연관관계 편의메서드
    public void setReviewBoard(ReviewBoard reviewBoard) {
        this.reviewBoard = reviewBoard;
        if (reviewBoard.getComments().contains(this)) {
            reviewBoard.getComments().add(this);
        }
    }

    //댓글 삭제 연관관계 편의메서드(연관관계 끊고 orphanremoval로 삭제)
    public void removeComment(User user, ReviewBoard reviewBoard) {
        user.getComments().remove(this);
        reviewBoard.getComments().remove(this);
    }

    //댓글 내용 수정 메서드
    public void updateComment(String newContent) {
        this.content = newContent;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Comment comment = (Comment) object;
        return Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(commentId);
    }
}
