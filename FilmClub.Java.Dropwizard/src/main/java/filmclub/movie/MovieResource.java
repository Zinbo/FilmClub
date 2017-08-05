package filmclub.movie;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Component
@Path("/movies")
@Api("/movies")
public class MovieResource {

    private MovieRepository movieRepository;

    @Autowired
    public MovieResource(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "gets all movies",
            response = Movie.class,
            responseContainer = "List"
    )
    public Response get(){
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "create a movie",
            response = Movie.class
    )
    public Response post(Movie movie){
        return Response.ok(movieRepository.save(movie)).build();
    }


}