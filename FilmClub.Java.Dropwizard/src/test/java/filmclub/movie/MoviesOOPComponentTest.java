package filmclub.movie;

import filmclub.EndToEndHelper;
import filmclub.application.ExceptionResponse;
import filmclub.movie.database.Movie;
import filmclub.movie.dto.CreateMovieDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class MoviesOOPComponentTest extends EndToEndHelper {

    //https://www.themoviedb.org/documentation/api
    //https://developers.themoviedb.org/3
    private Client client = ClientBuilder.newClient();
    
    @Test
    @Transactional
    public void post_withValidMovie_returns201() {
        //arrange
        int expected = 201;
        CreateMovieDto dto = new CreateMovieDto();
        dto.setExternalId(54335);

        //act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(dto));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void post_withValidMovie_returnsMovieWithId() {
        //arrange
        Movie expected = new Movie.Builder()
                .imageLink("/oql1WCn0WNMGBA8xwZt5FWnAcfW.jpg")
                .externalId(54335)
                .name("Christy - A New Beginning")
                .votes(0)
                .imdbId("")
                .build();
        CreateMovieDto dto = new CreateMovieDto();
        dto.setExternalId(54335);

        //act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(dto));
        Movie actual = response.readEntity(Movie.class);

        //assert
        Assertions.assertThat(actual).isEqualToIgnoringGivenFields(expected, "id");
        Assertions.assertThat(actual.getId()).isGreaterThan(0);
    }

    @Test
    @Transactional
    public void post_withInvalidExternalId_returns400() {
        //arrange
        CreateMovieDto dto = new CreateMovieDto();
        dto.setExternalId(1);
        int expected = 400;

        //act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(dto));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void post_withTheSameMovie_returnsMovieWithId() {
        //arrange
        int expected = 422;
        CreateMovieDto dto = new CreateMovieDto();
        dto.setExternalId(54335);

        //act
        client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(dto));
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().post(Entity.json(dto));
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
    
    @Test
    @Transactional
    public void get_withSearchParam_returns200() {
        // arrange
        int expected = 200;
        String name = "search";

        // act
        Response response = client.target(
                String.format("http://localhost:%d/movies?name=%s", RULE.getLocalPort(), name))
                .request().get();
        int actual = response.getStatus();

        // assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void get_withNoSearchParam_returns200() {
        // arrange
        int expected = 200;

        // act
        Response response = client.target(
                String.format("http://localhost:%d/movies", RULE.getLocalPort()))
                .request().get();
        int actual = response.getStatus();

        // assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void get_withEmptySearchParam_returns400() {
        // arrange
        int expected = 400;

        // act
        Response response = client.target(
                String.format("http://localhost:%d/movies?name=", RULE.getLocalPort()))
                .request().get();
        int actual = response.getStatus();

        // assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    @Transactional
    public void delete_withIdThatDoesntExist_returns404() {
        // arrange
        int expected = 404;

        // act
        Response response = client.target(
                String.format("http://localhost:%d/movies/123", RULE.getLocalPort()))
                .request().delete();
        int actual = response.getStatus();

        // assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
