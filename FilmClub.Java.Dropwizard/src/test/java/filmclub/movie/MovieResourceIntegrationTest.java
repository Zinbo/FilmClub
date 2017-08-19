package filmclub.movie;

import filmclub.EndToEndHelper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class MovieResourceIntegrationTest extends EndToEndHelper {

    //https://www.themoviedb.org/documentation/api
    private Client client = ClientBuilder.newClient();

    @Test
    public void get_withNoParams_returns200() {
        //arrange
        int expected = 200;

        //act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request()
                .get();
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void post_withValidMovie_returns201() {
        //arrange
        int expected = 201;
        Movie movie = new Movie();
        movie.setName("Movie 1");
        movie.setExternalId(1);
        movie.setImageLink("a");

        //act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(movie));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void post_withValidMovie_returnsMovieWithId() {
        //arrange
        Movie expected = new Movie();
        expected.setImageLink("blah");
        expected.setExternalId(2);
        expected.setName("movie1");

        //act

        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(expected));
        Movie actual = response.readEntity(Movie.class);

        //assert
        Assertions.assertThat(expected.getImageLink()).isEqualTo(actual.getImageLink());
        Assertions.assertThat(actual.getId()).isNotBlank();
    }

    @Test
    public void post_withTheSameMovie_returnsMovieWithId() {
        //arrange
        int expected = 422;
        Movie movie1 = new Movie();
        movie1.setExternalId(3);
        movie1.setName("movie2");
        movie1.setImageLink("blah");

        Movie movie2 = new Movie();
        movie2.setExternalId(3);
        movie2.setName("movie2");
        movie2.setImageLink("blah");

        //act
        client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(movie1));
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(movie2));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    public void post_withMissingParameters_returns400() {
        //arrange
    
        //act
        
        //assert
    }
}
