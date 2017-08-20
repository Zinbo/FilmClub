package filmclub.application;

import filmclub.application.springbundle.SpringBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.paradoxical.dropwizard.swagger.AppSwaggerConfiguration;
import io.paradoxical.dropwizard.swagger.bundles.SwaggerUIBundle;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DropwizardApplication extends Application<DropwizardConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropwizardConfiguration> bootstrap)  {
        bootstrap.addBundle(new SpringBundle<>());
        bootstrap.addBundle(
            new SwaggerUIBundle(env -> {
                return new AppSwaggerConfiguration(env) {
                    {
                        setTitle("My API");
                        setDescription("My API");
                        setSchemes(new String[]{"http"});

                        // The package name to look for swagger resources under
                        setResourcePackage("filmclub.movie");

                        setLicense("Apache 2.0");
                        setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html");

                        setContact("shanepeterjennings@gmail.com");

                        setVersion("1.0");
                    }
                };
            }));
    }

    @Override
    public void run(DropwizardConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(new RestExceptionsMapper());
    }

}