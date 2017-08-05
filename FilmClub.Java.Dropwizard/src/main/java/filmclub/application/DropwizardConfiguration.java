package filmclub.application;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DropwizardConfiguration extends Configuration {

    private SpringProperties springProperties;

    @JsonProperty(value = "spring")
    public void setSpringProperties(SpringProperties springProperties) {
        this.springProperties = springProperties;
    }

    @JsonProperty(value = "spring")
    public SpringProperties getSpringProperties() {
        return springProperties;
    }
}