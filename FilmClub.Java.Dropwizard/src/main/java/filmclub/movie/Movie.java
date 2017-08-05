package filmclub.movie;

import javax.persistence.*;

@Entity
@Table
public class Movie {
    @Id
    @GeneratedValue
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
