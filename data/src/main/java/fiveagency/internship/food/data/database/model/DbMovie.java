package fiveagency.internship.food.data.database.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import fiveagency.internship.food.data.network.model.ApiMovie;
import fiveagency.internship.food.domain.model.Movie;

@Entity(tableName = "movie")
public final class DbMovie {

    public static final DbMovie EMPTY = new DbMovie(0, "", false, "", "", "", "");

    @PrimaryKey
    private int id;
    private String title;
    private boolean isAdult;
    private String overview;
    private String releaseDate;
    private String imageSource;
    private String personalNote;

    public DbMovie(final int id, final String title, final boolean isAdult, final String overview, final String releaseDate, final String imageSource,
                   final String personalNote) {
        this.id = id;
        this.title = title;
        this.isAdult = isAdult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.imageSource = imageSource;
        this.personalNote = personalNote;
    }

    public DbMovie(final ApiMovie apiMovie) {
        this(apiMovie.id, apiMovie.title, apiMovie.isAdult, apiMovie.overview, apiMovie.releaseDate, apiMovie.imageSource, EMPTY.personalNote);
    }

    public DbMovie(final Movie movie) {
        this(movie.id, movie.title, movie.isAdult, movie.overview, movie.releaseDate, movie.imageSource, movie.personalNote);
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(final boolean adult) {
        isAdult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(final String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(final String imageSource) {
        this.imageSource = imageSource;
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(final String personalNote) {
        this.personalNote = personalNote;
    }
}
