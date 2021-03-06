package filmclub.movie.database;

import com.google.common.base.Strings;
import filmclub.application.HandledException;

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
    private Integer id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private int externalId;

    @Column(nullable = false)
    @NotNull
    private String imageLink;

    @Column
    private String imdbId;

    @Column
    private Integer votes;

    public Movie(){}

    private Movie(Builder builder) {
        setName(builder.name);
        setExternalId(builder.externalId);
        setImageLink(builder.imageLink);
        setImdbId(builder.imdbId);
        setVotes(builder.votes);
        if(!valid()) throw new HandledException("Movie must have external id, name, external id, and image link");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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


    private boolean valid() {
        return externalId > 0 &&
                !Strings.isNullOrEmpty(name) &&
                !Strings.isNullOrEmpty(imageLink);
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Integer getVotes() {
        return votes == null ? 0 : votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public static final class Builder {
        private Integer votes;
        private String name;
        private int externalId;
        private String imageLink;
        private String imdbId;

        public Builder() {
        }

        public Builder votes(int val) {
            votes = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder externalId(int val) {
            externalId = val;
            return this;
        }

        public Builder imageLink(String val) {
            imageLink = val;
            return this;
        }

        public Builder imdbId(String val) {
            imdbId = val;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}

