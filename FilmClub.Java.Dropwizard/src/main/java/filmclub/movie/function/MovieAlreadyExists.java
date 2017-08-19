package filmclub.movie.function;

import filmclub.movie.Movie;
import filmclub.movie.MovieRepository;
import org.springframework.stereotype.Component;

@Component
public class MovieAlreadyExists {

    private MovieRepository movieRepository;

    public MovieAlreadyExists(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean query(Movie movie) {
        return movieRepository.movieExistsWithExternalId(movie.getExternalId());
    }
}
