package com.teamproject.clonemoviepop.user.entity;

import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class ReviewBoardWish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewBoardWishId;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "REVIEW_BOARD_ID")
    private ReviewBoard reviewBoard;
}
