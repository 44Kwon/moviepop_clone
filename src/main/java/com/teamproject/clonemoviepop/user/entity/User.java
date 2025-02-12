package com.teamproject.clonemoviepop.user.entity;

import com.teamproject.clonemoviepop.audit.Auditable;
import com.teamproject.clonemoviepop.comment.entity.Comment;
import com.teamproject.clonemoviepop.movie_party.entity.MovieParty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "users")
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 100)
    private String nickname;

    @Column(nullable = false, length = 100)
    private String password;

    private String profileImage;

    @Column(nullable = false)
    private String name;

    private LocalDate birth;

    private float star = 0;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private UserStatus userStatus = UserStatus.USER_ACTIVE;

    public enum UserStatus {
        USER_ACTIVE("활동중인 계정"),
        USER_SLEEP("휴면 계정"),
        USER_WITHDRAW("탈퇴한 계정");

        @Getter
        private String status;

        UserStatus(String status) {
            this.status = status;
        }
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTag> userTags = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewBoardWish> reviewBoardWishes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CommentLike> commentLikes = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MovieParty> parties = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<MoviePartyUser> moviePartyUsers = new HashSet<>();


}
