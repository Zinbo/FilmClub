package filmclub.movie.function;

import filmclub.movie.database.Movie;
import filmclub.movie.proxy.themoviedb.MovieDto;
import org.springframework.stereotype.Component;

@Component
public class TranslateTheMovieDbDtoToMovie {
    public Movie query(MovieDto movieDto) {
        return new Movie.Builder()
                .externalId(movieDto.getId())
                .imageLink(movieDto.getPosterPath())
                .name(movieDto.getTitle())
                .build();
    }
}
