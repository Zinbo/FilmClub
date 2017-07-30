package filmclub.dropwizard;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

class HelloWorldConfiguration extends Configuration {

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