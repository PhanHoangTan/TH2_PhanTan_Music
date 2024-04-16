package entity;

import java.io.Serializable;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "Song")
public class Song implements Serializable{
    @Id
    @Column(name = "song_id")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String runtime;
    private String lyric;
    private String fileLink;

    @ManyToMany(mappedBy = "songs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Album> albums;
//	 @ManyToMany
//	    @JoinTable(
//	            name = "album_songs",
//	            joinColumns = @JoinColumn(name = "song_id"),
//	            inverseJoinColumns = @JoinColumn(name = "album_id")
//	    )
//	    private Set<Album> albums;

    public Song() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Song(String id, String name, String runtime, String lyric, String fileLink, Set<Album> albums) {
        super();
        this.id = id;
        this.name = name;
        this.runtime = runtime;
        this.lyric = lyric;
        this.fileLink = fileLink;
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

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(Set<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", name=" + name + ", runtime=" + runtime + ", lyric=" + lyric + ", fileLink="
                + fileLink + ", albums=" + albums + "]";
    }

}
