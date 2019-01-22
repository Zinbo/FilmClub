package filmclub.movie;

import filmclub.HandledException;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

public class Movie {
    @Id
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private int externalId;

    @NotNull
    private String imageLink;

    private String imdbId;

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
                !StringUtils.isEmpty(name) &&
                !StringUtils.isEmpty(imageLink);
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