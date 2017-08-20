package filmclub.movie;

import filmclub.EndToEndHelper;
import filmclub.movie.database.Movie;
import filmclub.movie.dto.CreateMovieDto;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

public class MovieResourceIntegrationTest extends EndToEndHelper {

    //https://www.themoviedb.org/documentation/api
    private Client client = ClientBuilder.newClient();

    @Test
    @Transactional
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
        Assertions.assertThat(actual.getId()).isNotBlank();
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
    public void post_withMissingParameters_returns400() {
        //arrange
    
        //act
        
        //assert
    }
}
