package filmclub.movie.function;

import filmclub.movie.database.Movie;
import filmclub.movie.proxy.themoviedb.TheMovieDbMovieDto;
import org.springframework.stereotype.Component;

@Component
public class TranslateTheMovieDbDtoToMovie {
    public Movie query(TheMovieDbMovieDto theMovieDbMovieDto) {
        return new Movie.Builder()
                .externalId(theMovieDbMovieDto.getId())
                .imageLink(theMovieDbMovieDto.getPosterPath())
                .name(theMovieDbMovieDto.getTitle())
                .build();
    }
}
