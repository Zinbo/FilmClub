package filmclub.movie;

import filmclub.application.ExceptionResponse;
import filmclub.movie.function.MovieAlreadyExists;
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
    private MovieAlreadyExists movieAlreadyExists;

    @Autowired
    public MovieResource(MovieRepository movieRepository, MovieAlreadyExists movieAlreadyExists) {
        this.movieRepository = movieRepository;
        this.movieAlreadyExists = movieAlreadyExists;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "gets all movies",
            response = Movie.class,
            responseContainer = "List")
    public Response get(){
        return Response.ok(new ArrayList<>()).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "create a movie",
            response = Movie.class)
    public Response post(Movie movie){
        if(movie == null) return Response.status(400).entity(new ExceptionResponse(400, "Movie payload cannot be null")).build();
        if(!movie.isValid()) return Response.status(400).entity(new ExceptionResponse(400, "Name, external ID, and imagePath are all required")).build();
        boolean alreadyExists = movieAlreadyExists.query(movie);
        if(alreadyExists) return Response.status(422).entity(new ExceptionResponse(422, "Movie with this external id already exists")).build();

        return Response.status(201).entity(movieRepository.save(movie)).build();
    }


}
