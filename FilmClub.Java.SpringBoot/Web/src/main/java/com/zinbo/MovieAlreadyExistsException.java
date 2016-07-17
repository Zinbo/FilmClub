package com.zinbo;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class MovieAlreadyExistsException extends RuntimeException {
    public MovieAlreadyExistsException(String imdbId){
        super("Movie already exists with imdb id '" + imdbId + "'.");
    }
}
