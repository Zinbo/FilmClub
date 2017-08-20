package filmclub.movie.proxy.themoviedb;

import filmclub.application.HandledException;
import filmclub.movie.proxy.ClientAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import java.util.Optional;

@Component
public class TheMovieDbProxy {
    //https://api.themoviedb.org/3/movie/76341?api_key=c99917ae489bca2e3ad805c0ad92546b

    @Autowired
    private ClientAdapter client;

    public Optional<TheMovieDbMovieDto> getMovieById(int externalId) {
        Response response = client.doGet(String.format(TheMovieDbProxyProperties.getMovieByIdUrl, externalId));
        if(response.getStatus() == 200) return Optional.of(response.readEntity(TheMovieDbMovieDto.class));
        if(response.getStatus() == 404) return Optional.empty();
        throw new HandledException(String.format("Could not retrieve movie details, status code: %d, reason: %s", response.getStatus(), response.readEntity(String.class)));
    }
}
