package filmclub.movie.function;

import filmclub.movie.database.MovieRepository;
import org.springframework.stereotype.Component;

@Component
public class ExternalIdAlreadyMappedToMovie {

    private MovieRepository movieRepository;

    public ExternalIdAlreadyMappedToMovie(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public boolean query(int externalId) {
        return movieRepository.movieExistsWithExternalId(externalId);
    }
}
