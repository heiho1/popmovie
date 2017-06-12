package com.example.heiho1.popmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * Container of one or more VideoSummary instances associated with a movie MovieDetailResponse
 */
public class Videos implements Serializable, Parcelable
{

    @SerializedName("results")
    @Expose
    private List<Video> results = null;
    public final static Parcelable.Creator<Videos> CREATOR = new Creator<Videos>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Videos createFromParcel(Parcel in) {
            Videos instance = new Videos();
            in.readList(instance.results, (com.example.heiho1.popmovies.model.Video.class.getClassLoader()));
            return instance;
        }

        public Videos[] newArray(int size) {
            return (new Videos[size]);
        }

    };

    private final static long serialVersionUID = 2519386377356177650L;

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

    public Videos withResults(List<Video> results) {
        this.results = results;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(results).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Videos) == false) {
            return false;
        }
        Videos rhs = ((Videos) other);
        return new EqualsBuilder().append(results, rhs.results).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}



