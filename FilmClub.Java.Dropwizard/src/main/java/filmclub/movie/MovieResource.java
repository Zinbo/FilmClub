package filmclub.movie;

import filmclub.application.ExceptionResponse;
import filmclub.movie.database.Movie;
import filmclub.movie.database.MovieRepository;
import filmclub.movie.dto.CreateMovieDto;
import filmclub.movie.function.ExternalIdAlreadyMappedToMovie;
import filmclub.movie.function.GetMovieDetailsByExternalId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;

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
            value = "gets all movies, can pass search param to filter",
            response = Movie.class,
            responseContainer = "List")
    public Response get(@QueryParam("name") String searchTerm){
        if(StringUtils.isWhitespace(searchTerm)) return Response
                .status(400)
                .entity(new ExceptionResponse(400,"Search param cannot be empty"))
                .build();

        if(searchTerm == null) {
            List<Movie> allMovies = movieRepository.findAll();
            allMovies.sort(Comparator.comparing(Movie::getName));
            return Response.ok(allMovies).build();
        }
        return Response.ok(movieRepository.moviesWhichContainName(searchTerm)).build();
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

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        if(id == null) return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponse(400, "Id cannot be empty"))
                .build();
        Movie movie = movieRepository.findOne(id);
        if(movie == null) return Response.status(Response.Status.NOT_FOUND).build();
        movieRepository.delete(movie);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/vote")
    public Response vote(@PathParam("id") Integer id, VoteDto voteDto) {
        if(id == null || voteDto == null) return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ExceptionResponse(400, "Id and dto cannot be empty"))
                .build();
        Movie movie = movieRepository.findOne(id);
        if(movie == null) return Response.status(Response.Status.NOT_FOUND).build();
        movie.setVotes(movie.getVotes() + voteDto.getScore());
        movieRepository.save(movie);
        return Response.ok().build();
    }
}
