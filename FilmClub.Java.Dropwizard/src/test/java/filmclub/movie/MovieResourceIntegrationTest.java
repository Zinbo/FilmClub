package filmclub.movie;

import filmclub.EndToEndHelper;
import io.dropwizard.client.JerseyClientBuilder;
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
        int expected = 200;
        Movie movie = new Movie();

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
        movie1.setThemoviedbId(1);

        Movie movie2 = new Movie();
        movie2.setThemoviedbId(2);

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
}
