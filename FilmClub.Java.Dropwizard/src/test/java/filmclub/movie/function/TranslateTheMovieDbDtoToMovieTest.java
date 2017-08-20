package filmclub.movie.function;

import filmclub.movie.database.Movie;
import filmclub.movie.proxy.themoviedb.TheMovieDbMovieDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TranslateTheMovieDbDtoToMovieTest {

    private TranslateTheMovieDbDtoToMovie translateTheMovieDbDtoToMovie = new TranslateTheMovieDbDtoToMovie();

    @Test
    public void query_withDto_returnsMovie() {
        //arrange
        String name = "movieName";
        String path = "link";
        int id = 1;
        Movie expected = new Movie.Builder()
                .name(name)
                .externalId(id)
                .imageLink(path)
                .build();

        TheMovieDbMovieDto dto = new TheMovieDbMovieDto();
        dto.setId(id);
        dto.setPosterPath(path);
        dto.setTitle(name);

        //act
        Movie actual = translateTheMovieDbDtoToMovie.query(dto);

        //assert
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
