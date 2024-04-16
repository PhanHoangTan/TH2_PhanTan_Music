package entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Artist")
public class Artist implements Serializable{
    @Id
    @Column(name = "artist_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private LocalDate birthDate;
    private String url;

    @ManyToMany(mappedBy = "artists", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums;

    public Artist() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Artist(String id, String name, LocalDate birthDate, String url, Set<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.url = url;
        this.albums = albums;
    }

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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Artist [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", url=" + url + ", albums="
                + albums + "]";
    }

}
