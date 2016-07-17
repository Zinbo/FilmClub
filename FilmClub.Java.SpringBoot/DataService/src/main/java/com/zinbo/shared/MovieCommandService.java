package com.zinbo.shared;

import com.zinbo.shared.Movie;
import com.zinbo.shared.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieCommandService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieCommandService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie addMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public Optional<Movie> findMovieByImdbId(String imdbId){
        return movieRepository.findByImdbId(imdbId);
    }

}
