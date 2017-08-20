package filmclub.movie.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Component
public class ClientAdapter {
    private Client client = ClientBuilder.newClient();

    @Autowired
    public ClientAdapter(){

    }

    public Response doGet(String url) {
        return client.target(url).request().get();
    }
}
