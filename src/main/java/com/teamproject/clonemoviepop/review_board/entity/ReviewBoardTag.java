package com.teamproject.clonemoviepop.review_board.entity;

import com.teamproject.clonemoviepop.tag.entity.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class ReviewBoardTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewBoardTagId;

    @ManyToOne
    @JoinColumn(name = "TAG_ID")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "REVIEW_BOARD_ID")
    private ReviewBoard reviewBoard;
}
