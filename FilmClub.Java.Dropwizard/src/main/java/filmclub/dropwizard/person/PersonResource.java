package filmclub.dropwizard.person;

import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/person")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    private PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response savePerson(Person person) {
        personRepository.save(person);
        return Response.ok().build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerson(@PathParam("id") int id){
        return Response.ok(personRepository.findOne(id)).build();
    }
}
