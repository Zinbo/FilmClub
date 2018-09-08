package filmclub.movie.function;

import filmclub.application.HandledException;
import filmclub.movie.database.Movie;
import filmclub.movie.proxy.themoviedb.MovieDto;
import filmclub.movie.proxy.themoviedb.TheMovieDbProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetMovieDetailsByExternalId {

    private TheMovieDbProxy theMovieDbProxy;
    private TranslateTheMovieDbDtoToMovie translateTheMovieDbDtoToMovie;

    @Autowired
    public GetMovieDetailsByExternalId(TheMovieDbProxy theMovieDbProxy, TranslateTheMovieDbDtoToMovie translateTheMovieDbDtoToMovie) {
        this.theMovieDbProxy = theMovieDbProxy;
        this.translateTheMovieDbDtoToMovie = translateTheMovieDbDtoToMovie;
    }

    public Movie query(int externalId) {
        Optional<MovieDto> dto = theMovieDbProxy.getMovieById(externalId);
        dto.orElseThrow(() ->
                new HandledException("external ID does not map to a movie in The Movie DB"));
        return translateTheMovieDbDtoToMovie.query(dto.get());
    }
}
