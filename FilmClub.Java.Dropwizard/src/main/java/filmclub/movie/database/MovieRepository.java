package filmclub.movie.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    @Query("select count(m) > 0 from Movie m " +
            "where m.externalId = :externalId")
    boolean movieExistsWithExternalId(@Param("externalId") int externalId);

    @Query("select m from Movie m " +
            "where LOWER(m.name) LIKE CONCAT('%',LOWER(:name),'%')")
    List<Movie> moviesWhichContainName(@Param("name") String name);
}
