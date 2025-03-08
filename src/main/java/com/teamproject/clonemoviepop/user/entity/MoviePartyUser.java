package com.teamproject.clonemoviepop.user.entity;

import com.teamproject.clonemoviepop.movie_party.entity.MovieParty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class MoviePartyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long moviePartyUserId;
    private String profileImage;    //게시판에 보여지는 유저프로필 이미지

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "movie_party_id")
    private MovieParty movieParty;

    //객체 자체 비교는 무겁고 비효율적. 필드 전부 비교하기 때문. => 아이디 값만 비교하자~
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MoviePartyUser that = (MoviePartyUser) object;
        return Objects.equals(user.getUserId(), that.user.getUserId()) && Objects.equals(movieParty.getMoviePartyId(), that.movieParty.getMoviePartyId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getUserId(), movieParty.getMoviePartyId());
    }
}
