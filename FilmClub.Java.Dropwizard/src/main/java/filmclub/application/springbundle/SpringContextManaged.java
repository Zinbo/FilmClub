package filmclub.application.springbundle;

import io.dropwizard.lifecycle.Managed;
import org.springframework.context.ConfigurableApplicationContext;


class SpringContextManaged implements Managed {
    private final ConfigurableApplicationContext context;

    SpringContextManaged(final ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void start() throws Exception {
        // do nothing
    }

    @Override
    public void stop() throws Exception {
        context.close();
    }
}
