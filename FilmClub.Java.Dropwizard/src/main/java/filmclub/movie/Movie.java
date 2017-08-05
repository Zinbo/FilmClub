package filmclub.movie;

import javax.persistence.*;

//We need @Table because when hibernate tries to create these tables, it will try to create a table called "Movie", however postgres is not case
//sensitive, so it will create a table called "movie". Next time, hibernate will look for a table called "Movie", not find it, so will try to create
//again. Postgres will complain, because it already has a table called "movie"
@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column
    private String name;

    @Column
    private int themoviedbId;

    @Column
    private String imageLink;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThemoviedbId() {
        return themoviedbId;
    }

    public void setThemoviedbId(int themoviedbId) {
        this.themoviedbId = themoviedbId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
