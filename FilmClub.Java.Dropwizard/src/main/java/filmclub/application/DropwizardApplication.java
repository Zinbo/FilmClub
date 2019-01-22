package filmclub.application;

import filmclub.application.springbundle.SpringBundle;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.paradoxical.dropwizard.swagger.AppSwaggerConfiguration;
import io.paradoxical.dropwizard.swagger.bundles.SwaggerUIBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.springframework.beans.BeansException;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

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
            new SwaggerUIBundle(env ->
                 new AppSwaggerConfiguration(env) {
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
            }));
    }

    @Override
    public void run(DropwizardConfiguration configuration,
                    Environment environment) {
        environment.jersey().register(new RestExceptionsMapper());

        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

}