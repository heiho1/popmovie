package com.example.heiho1.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * A collection to which a movie belongs, such as Star Wars or Harry Potter
 */
public class MovieCollection implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    public final static Parcelable.Creator<MovieCollection> CREATOR = new Creator<MovieCollection>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieCollection createFromParcel(Parcel in) {
            MovieCollection instance = new MovieCollection();
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public MovieCollection[] newArray(int size) {
            return (new MovieCollection[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7912534971453099820L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieCollection withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieCollection withName(String name) {
        this.name = name;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public MovieCollection withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public MovieCollection withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(posterPath).append(backdropPath).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieCollection) == false) {
            return false;
        }
        MovieCollection rhs = ((MovieCollection) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(posterPath, rhs.posterPath).append(backdropPath, rhs.backdropPath).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(name);
        dest.writeValue(posterPath);
        dest.writeValue(backdropPath);
    }

    public int describeContents() {
        return 0;
    }

}
