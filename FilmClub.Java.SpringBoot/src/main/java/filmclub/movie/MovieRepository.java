package filmclub.movie;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface MovieRepository extends MongoRepository<Movie, Integer> {

    // why isn't this getting picked up?
    List<Movie> findByName(@Param("name") String name);
}
