package com.zinbo.shared;

import java.util.Optional;

public interface MovieRepository extends Repository<Movie> {
    Optional<Movie> findByImdbId(String imdbId);
}
