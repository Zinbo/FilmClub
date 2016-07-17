package com.zinbo;

import com.zinbo.shared.Movie;
import com.zinbo.shared.MovieCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.function.Consumer;

@RestController
public class AddMovieController {

    @Autowired
    private MovieCommandService movieCommandService;


    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    public ResponseEntity<?> addMovie(@RequestBody Movie movie)
    {
        validateNewMovie(movie.getImdbId());
        return new ResponseEntity<>(movieCommandService.addMovie(movie), null, HttpStatus.CREATED);
    }

    private void validateNewMovie(String imdbId){
        if(movieCommandService.findMovieByImdbId(imdbId).isPresent()) throw new MovieAlreadyExistsException(imdbId);
    }
}
