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
 * A serializable and parcelable representation of a themoviedb.org API search list response
 *
 * @see http://www.jsonschema2pojo.org/
 */
public class MovieListResponse implements Serializable, Parcelable
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<MovieSummary> results = null;

    public final static Parcelable.Creator<MovieListResponse> CREATOR = new Creator<MovieListResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieListResponse createFromParcel(Parcel in) {
            MovieListResponse instance = new MovieListResponse();
            instance.page = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.totalPages = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.results, (MovieSummary.class.getClassLoader()));
            return instance;
        }

        public MovieListResponse[] newArray(int size) {
            return (new MovieListResponse[size]);
        }

    }
            ;
    private final static long serialVersionUID = 6908510600247527177L;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieSummary> getResults() {
        return results;
    }

    public void setResults(List<MovieSummary> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(page).append(totalResults).append(totalPages).append(results).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieListResponse) == false) {
            return false;
        }
        MovieListResponse rhs = ((MovieListResponse) other);
        return new EqualsBuilder().append(page, rhs.page).append(totalResults, rhs.totalResults).append(totalPages, rhs.totalPages).append(results, rhs.results).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(page);
        dest.writeValue(totalResults);
        dest.writeValue(totalPages);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}