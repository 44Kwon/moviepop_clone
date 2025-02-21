package com.teamproject.clonemoviepop.movie.mapper;

import com.teamproject.clonemoviepop.movie.dto.MovieDto;
import com.teamproject.clonemoviepop.movie.entity.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieDto.Response movieToMovieResponseDto(Movie movie);
    List<MovieDto.Response> moviesToMovieResponseDtos(List<Movie> movie);
}
