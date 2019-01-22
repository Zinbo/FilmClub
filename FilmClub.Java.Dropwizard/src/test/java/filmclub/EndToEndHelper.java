package filmclub;

import filmclub.application.DropwizardApplication;
import filmclub.application.DropwizardConfiguration;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;

import javax.ws.rs.client.Client;

public abstract class EndToEndHelper {

    @Rule
    public final DropwizardAppRule<DropwizardConfiguration> RULE =
            new DropwizardAppRule<>(DropwizardApplication.class, ResourceHelpers.resourceFilePath("test.yaml"));
}
