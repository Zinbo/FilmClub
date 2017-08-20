package filmclub.application;

import filmclub.movie.proxy.themoviedb.TheMovieDbProxyProperties;
import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DropwizardConfiguration extends Configuration {

    private SpringProperties springProperties;
    private TheMovieDbProxyProperties theMovieDbProxyProperties;

    @JsonProperty(value = "spring")
    public void setSpringProperties(SpringProperties springProperties) {
        this.springProperties = springProperties;
    }

    @JsonProperty(value = "spring")
    public SpringProperties getSpringProperties() {
        return springProperties;
    }


    @JsonProperty(value = "theMovieDbProxy")
    public TheMovieDbProxyProperties getTheMovieDbProxyProperties() {
        return theMovieDbProxyProperties;
    }

    @JsonProperty(value = "theMovieDbProxy")
    public void setTheMovieDbProxyProperties(TheMovieDbProxyProperties theMovieDbProxyProperties) {
        this.theMovieDbProxyProperties = theMovieDbProxyProperties;
    }
}