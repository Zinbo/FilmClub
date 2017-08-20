package filmclub.movie.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query("select count(m) > 0 from Movie m " +
            "where m.externalId = :externalId")
    boolean movieExistsWithExternalId(@Param("externalId") int externalId);
}
