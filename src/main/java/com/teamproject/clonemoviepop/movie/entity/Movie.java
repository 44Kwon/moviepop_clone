package com.teamproject.clonemoviepop.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;
    private String title;
    private boolean adulted;

    //외부 API에서 movie를 가져와서 넣기 위해서
    public Movie(String title, boolean adulted) {
        this.title = title;
        this.adulted = adulted;
    }

    //굳이 reviewBoard와 양방향 연관관계를 맺을 이유가 없음.

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Movie movie = (Movie) object;
        return adulted == movie.adulted && Objects.equals(title, movie.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, adulted);
    }
}
