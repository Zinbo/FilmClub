package filmclub.movie.proxy.themoviedb;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class TheMovieDbProxyProperties {
    public static String getMovieByIdUrl;

    public String getGetMovieByIdUrl() {
        return TheMovieDbProxyProperties.getMovieByIdUrl;
    }

    public void setGetMovieByIdUrl(String getMovieByIdUrl) {
        TheMovieDbProxyProperties.getMovieByIdUrl = getMovieByIdUrl;
    }
}
