package filmclub.movie;

import filmclub.TestConfiguration;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.assertj.core.api.Assertions;
import org.junit.ClassRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@ContextConfiguration(classes = TestConfiguration.class)
public class MovieResourceIntegrationTest {

    @Autowired
    private static MovieRepository movieRepository;

    //https://www.themoviedb.org/documentation/api

    @ClassRule
    public static final ResourceTestRule movieResource = ResourceTestRule.builder()
            .addResource(new MovieResource(movieRepository))
            .build();

    @Test
    public void get_withNoParams_returns200() {
        //arrange
        int expected = 200;

        //act
        Response response = movieResource.target("/movies").request().get();
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void post_withValidMovie_returns201() {
        //arrange
        int expected = 200;
        Movie movie = new Movie();

        //act
        Response response = movieResource.target("/movies").request().post(Entity.json(movie));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void post_withValidMovie_returnsMovieWithId() {
        //arrange
        Movie expected = new Movie();
        expected.setImageLink("blah");

        //act
        Response response = movieResource.target("/movies").request().post(Entity.json(expected));
        Movie actual = response.readEntity(Movie.class);

        //assert
        Assertions.assertThat(expected.getImageLink()).isEqualTo(actual.getImageLink());
        Assertions.assertThat(actual.getId()).isNotBlank();
    }
}
