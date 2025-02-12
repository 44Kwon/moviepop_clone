package com.teamproject.clonemoviepop.movie_party.entity;

import com.teamproject.clonemoviepop.review_board.entity.ReviewBoard;
import com.teamproject.clonemoviepop.user.entity.MoviePartyUser;
import com.teamproject.clonemoviepop.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Getter
public class MovieParty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moviePartyId;

    @Column(nullable = false, length = 300)
    private String title;
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime meetingDate;
    @Column(nullable = false, length = 250)
    private String location;
    @Column(nullable = false)
    private Integer maxCapacity;
    @Column(nullable = false)
    private Integer currentParticipant = 1;
    @Column(nullable = false, length = 1000)
    private String content;
    @Column(nullable = false, length = 200)
    private String movieTitle;

    @ManyToOne
    @JoinColumn(name = "review_board_id")
    private ReviewBoard reviewBoard;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "movieParty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MoviePartyUser> moviePartyUsers = new HashSet<>();



}
