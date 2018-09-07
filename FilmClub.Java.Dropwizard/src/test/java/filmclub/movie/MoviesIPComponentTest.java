package filmclub.movie;

import filmclub.TestConfiguration;
import filmclub.application.ExceptionResponse;
import filmclub.application.SpringConfiguration;
import filmclub.movie.database.Movie;
import filmclub.movie.database.MovieRepository;
import org.assertj.core.api.Assertions;
import org.eclipse.collections.impl.list.mutable.FastList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(classes = TestConfiguration.class)
public class MoviesIPComponentTest {

    @Autowired
    private MovieResource movieResource;
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Transactional
    public void getMovies_withoutNullSearchParam_returnsAllMovies() {
        // arrange
        Movie movie1 = new Movie();
        movie1.setExternalId(1234);
        movie1.setImageLink("12345");
        movie1.setName("movie");

        Movie movie2 = new Movie();
        movie2.setExternalId(1234);
        movie2.setImageLink("12345");
        movie2.setName("movie2");
        movieRepository.save(FastList.newListWith(movie1, movie2));

        // act
        Response response = movieResource.get(null);
        List<Movie> actual = (List<Movie>) response.getEntity();

        // assert
        Assertions.assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .usingElementComparatorIgnoringFields("id")
                .containsExactly(movie1, movie2);

    }

    @Test
    @Transactional
    public void getMovies_withSearchParamThatMatchesTwoMovieNames_returnsThoseTwoMovies() {
        // arrange
        Movie matchingMovie1 = new Movie();
        matchingMovie1.setName("name1");
        matchingMovie1.setImageLink("link1");
        matchingMovie1.setExternalId(1);
        Movie matchingMovie2 = new Movie();
        matchingMovie2.setName("name2");
        matchingMovie2.setImageLink("link2");
        matchingMovie2.setExternalId(2);
        Movie nonmatchingMovie = new Movie();
        nonmatchingMovie.setName("different");
        nonmatchingMovie.setExternalId(3);
        nonmatchingMovie.setImageLink("link2");
        movieRepository.save(FastList.newListWith(matchingMovie1, matchingMovie2, nonmatchingMovie));

        // act
        Response response = movieResource.get("name");
        List<Movie> actual = (List<Movie>) response.getEntity();

        // assert
        Assertions.assertThat(actual)
                .usingRecursiveFieldByFieldElementComparator()
                .usingElementComparatorIgnoringFields("id")
                .containsExactlyInAnyOrder(matchingMovie1, matchingMovie2);
    }

    @Test
    @Transactional
    public void getMovies_withSearchParamThatMatchesNoMovies_returnsEmptyList() {
        // arrange
        Movie movie = new Movie();
        movie.setName("name1");
        movie.setImageLink("link1");
        movie.setExternalId(1);
        movieRepository.save(FastList.newListWith(movie));

        // act
        Response response = movieResource.get("not");
        List<Movie> actual = (List<Movie>) response.getEntity();

        // assert
        Assertions.assertThat(actual).isEmpty();
    }

    @Test
    @Transactional
    public void getMovies_withEmptySearchParam_returnsResponseSayingNeedSearchParam() {
        // arrange
        ExceptionResponse expected = new ExceptionResponse(400, "Search param cannot be empty");

        // act
        Response response = movieResource.get("");
        ExceptionResponse actual = (ExceptionResponse) response.getEntity();

        // assert
        Assertions.assertThat(actual).isEqualToComparingFieldByFieldRecursively(expected);
    }
}
