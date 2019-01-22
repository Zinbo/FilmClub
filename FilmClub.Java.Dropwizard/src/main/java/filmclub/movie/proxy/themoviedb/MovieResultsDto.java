package filmclub.movie.proxy.themoviedb;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MovieResultsDto {
    @JsonProperty(value = "total_results")
    private int totalResults;
    private List<MovieResultDto> results;

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<MovieResultDto> getResults() {
        return results;
    }

    public void setResults(List<MovieResultDto> results) {
        this.results = results;
    }
}
