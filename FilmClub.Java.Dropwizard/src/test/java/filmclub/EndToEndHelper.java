package filmclub;

import filmclub.application.DropwizardApplication;
import filmclub.application.DropwizardConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;

import javax.ws.rs.client.Client;

public abstract class EndToEndHelper {

    @ClassRule
    public static final DropwizardAppRule<DropwizardConfiguration> RULE =
            new DropwizardAppRule<>(DropwizardApplication.class, ResourceHelpers.resourceFilePath("test.yaml"));
}
