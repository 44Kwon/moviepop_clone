package com.teamproject.clonemoviepop.review_board.entity;

import com.teamproject.clonemoviepop.audit.Auditable;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.movie.entity.Movie;
import com.teamproject.clonemoviepop.movie_party.entity.MovieParty;
import com.teamproject.clonemoviepop.user.entity.ReviewBoardWish;
import com.teamproject.clonemoviepop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
public class ReviewBoard extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewBoardId;

    @Column(length = 300)
    private String title;

    @Column(length = 1000)
    private String review;

    private int wish;
    private String thumbnail;
    private boolean adulted;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "reviewBoard")
    private List<ReviewBoardWish> reviewBoardWishes = new ArrayList<>();

    @OneToMany(mappedBy = "reviewBoard")
    private List<ReviewBoardTag> reviewBoardTags = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    @OneToMany(mappedBy = "reviewBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieParty> parties = new ArrayList<>();

    @OneToMany(mappedBy = "reviewBoard", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments = new LinkedHashSet<>();

    //추천게시판 관련은 나중에 하기
}
