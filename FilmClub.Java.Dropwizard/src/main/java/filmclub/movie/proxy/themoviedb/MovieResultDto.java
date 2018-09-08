package filmclub.movie.proxy.themoviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieResultDto {
    private int id;
    private String title;
    @JsonProperty(value = "poster_path")
    private String posterPath;

}
