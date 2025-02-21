package com.teamproject.clonemoviepop.movie.service;

import com.teamproject.clonemoviepop.movie.dto.MovieDto;
import com.teamproject.clonemoviepop.movie.entity.Movie;
import com.teamproject.clonemoviepop.movie.mapper.MovieMapper;
import com.teamproject.clonemoviepop.movie.repository.MovieRepository;
import com.teamproject.clonemoviepop.user.entity.User;
import com.teamproject.clonemoviepop.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieMapper mapper;

    public void makeInitData(List<Movie> movieList) {
        movieRepository.saveAll(movieList);
    }

    @Transactional(readOnly = true)
    public Movie findMovie(Long movieId) {
        return findVerifiedMovieId(movieId);
    }

    public Set<Movie> findMovies() {
        return movieRepository.findAll().stream().collect(Collectors.toSet());
    }

    public List<Movie> findSearchedMovies(String title) {
        List<Movie> movies = movieRepository.findByTitleContains(title);
        if(movies.isEmpty()) throw new IllegalArgumentException("나중에");
        return movies;
    }

    public List<MovieDto.Response> searchMovies(User user, String query) {
        Period age = UserUtils.getAge(user);

        if (age.getYears() >= 19) {
            return mapper.moviesToMovieResponseDtos(movieRepository.findByTitleContains(query));
        } else {
            return mapper.moviesToMovieResponseDtos(movieRepository.findByAdultedIsFalseAndTitleContains(query));
        }
    }


    private Movie findVerifiedMovieId(Long movieId) {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        return optionalMovie.orElseThrow(() -> new IllegalArgumentException("나중에"));
    }
}
