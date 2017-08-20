package filmclub.movie.function;

import filmclub.application.HandledException;
import filmclub.movie.database.Movie;
import filmclub.movie.proxy.themoviedb.TheMovieDbMovieDto;
import filmclub.movie.proxy.themoviedb.TheMovieDbProxy;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Optional;

public class GetMovieDetailsByExternalIdTest {
    private TheMovieDbProxy theMovieDbProxy = Mockito.mock(TheMovieDbProxy.class);
    private TranslateTheMovieDbDtoToMovie translateTheMovieDbDtoToMovie = Mockito.mock(TranslateTheMovieDbDtoToMovie.class);
    private GetMovieDetailsByExternalId target;

    @Before
    public void setup() {
        target = new GetMovieDetailsByExternalId(theMovieDbProxy, translateTheMovieDbDtoToMovie);
    }

    @Test
    public void query_withValidExternalId_returnsTranslatedMovie() {
        //arrange
        int externalId = 1;
        TheMovieDbMovieDto dto = new TheMovieDbMovieDto();
        dto.setId(1);
        Movie expected = new Movie();
        Mockito.when(theMovieDbProxy.getMovieById(externalId)).thenReturn(Optional.of(dto));
        Mockito.when(translateTheMovieDbDtoToMovie.query(dto)).thenReturn(expected);

        //act
        Movie actual = target.query(externalId);

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test(expected = HandledException.class)
    public void query_withInvalidExternalId_throwsException() {
        //arrange
        int id = 1;
        Mockito.when(theMovieDbProxy.getMovieById(id)).thenReturn(Optional.empty());

        //act
        Movie actual = target.query(id);

        //assert
    }
}
