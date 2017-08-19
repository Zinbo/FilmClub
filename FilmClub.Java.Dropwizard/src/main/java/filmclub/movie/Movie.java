package filmclub.movie;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Strings;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
    @NotNull
    private String name;

    @Column
    @NotNull
    private int externalId;

    @Column
    @NotNull
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


    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getExternalId() {
        return externalId;
    }

    public void setExternalId(int externalId) {
        this.externalId = externalId;
    }

    @JsonIgnore
    public boolean isValid() {
        return externalId > 0 &&
                !Strings.isNullOrEmpty(name) &&
                !Strings.isNullOrEmpty(imageLink);
    }
}
