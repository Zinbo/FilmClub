package filmclub;

import io.dropwizard.client.JerseyClientBuilder;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

public class PingTest extends EndToEndHelper {

    @Test
    public void ping_withDropwizardDeployed_returns200() {
        //arrange
        int expected = 200;

        //act
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");

        Response response = client.target(
                String.format("http://localhost:%d/ping", RULE.getAdminPort()))
                .request()
                .get();
        int actual = response.getStatus();

        //assert
        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
