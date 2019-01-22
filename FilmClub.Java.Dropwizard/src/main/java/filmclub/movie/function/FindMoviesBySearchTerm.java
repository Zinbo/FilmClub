package filmclub.movie.function;

import filmclub.application.HandledException;
import filmclub.movie.proxy.themoviedb.MovieResultsDto;
import filmclub.movie.proxy.themoviedb.TheMovieDbProxy;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class FindMoviesBySearchTerm {

    private TheMovieDbProxy theMovieDbProxy;

    @Autowired
    public FindMoviesBySearchTerm(TheMovieDbProxy theMovieDbProxy) {
        this.theMovieDbProxy = theMovieDbProxy;
    }

    public MovieResultsDto query(String searchTerm) {
        if(StringUtils.isBlank(searchTerm)) throw new HandledException("search term cannot be null");
        return theMovieDbProxy.searchForMoviesByName(searchTerm);
    }
}
