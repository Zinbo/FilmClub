package filmclub.movie;

import filmclub.application.ExceptionResponse;
import filmclub.movie.database.Movie;
import filmclub.movie.database.MovieRepository;
import filmclub.movie.dto.CreateMovieDto;
import filmclub.movie.function.ExternalIdAlreadyMappedToMovie;
import filmclub.movie.function.GetMovieDetailsByExternalId;
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
    private ExternalIdAlreadyMappedToMovie externalIdAlreadyMappedToMovie;
    private GetMovieDetailsByExternalId getMovieDetailsByExternalId;

    @Autowired
    public MovieResource(MovieRepository movieRepository, ExternalIdAlreadyMappedToMovie externalIdAlreadyMappedToMovie, GetMovieDetailsByExternalId getMovieDetailsByExternalId) {
        this.movieRepository = movieRepository;
        this.externalIdAlreadyMappedToMovie = externalIdAlreadyMappedToMovie;
        this.getMovieDetailsByExternalId = getMovieDetailsByExternalId;
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
            response = CreateMovieDto.class)
    public Response post(CreateMovieDto movieDto){
        if(movieDto == null) return Response.status(400).entity(new ExceptionResponse(400, "Movie payload cannot be null")).build();
        boolean alreadyExists = externalIdAlreadyMappedToMovie.query(movieDto.getExternalId());
        if(alreadyExists) return Response.status(422).entity(new ExceptionResponse(422, "Movie with this external id already exists")).build();
        Movie movie = getMovieDetailsByExternalId.query(movieDto.getExternalId());
        return Response.status(201).entity(movieRepository.save(movie)).build();
    }


}
