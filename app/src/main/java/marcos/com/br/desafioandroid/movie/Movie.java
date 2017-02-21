package marcos.com.br.desafioandroid.movie;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;


/**
 * Created by marcos on 19/02/17.
 */

public class Movie  implements Serializable{

    private String title;
    private String year;
    private String genre;
    private String duration;
    private String summary;
    private String poster;
    private String rating;

    public Movie(String title, String year, String genre, String duration,
                 String summary, String poster, String rating) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.duration = duration;
        this.summary = summary;
        this.poster = poster;
        this.rating = rating;
    }

    public String getduration() {
        return duration;
    }

    public void setduration(String duration) {
        this.duration = duration;
    }

    public String getsummary() {
        return summary;
    }

    public void setsummary(String summary) {
        this.summary = summary;
    }

    public String getgenre() {
        return genre;
    }

    public void setgenre(String genre) {
        this.genre = genre;
    }

    public String getyear() {
        return year;
    }

    public void setyear(String year) {
        this.year = year;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public String getposter() {
        return poster;
    }

    public void setposter(String poster) {
        this.poster = poster;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



}
