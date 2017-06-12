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
 * Created by heiho1 on 6/9/17.
 */
public class MovieDetailResponse implements Serializable, Parcelable {

    @SerializedName("adult")
    @Expose
    private Boolean adult;
    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    @Expose
    private MovieCollection collection;
    @SerializedName("budget")
    @Expose
    private Integer budget;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("homepage")
    @Expose
    private String homepage;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("imdb_id")
    @Expose
    private String imdbId;
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;
    @SerializedName("original_title")
    @Expose
    private String originalTitle;
    @SerializedName("overview")
    @Expose
    private String overview;
    @SerializedName("popularity")
    @Expose
    private Double popularity;
    @SerializedName("poster_path")
    @Expose
    private String posterPath;
    @SerializedName("production_companies")
    @Expose
    private List<ProductionCompany> productionCompanies = null;
    @SerializedName("production_countries")
    @Expose
    private List<ProductionCountry> productionCountries = null;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("revenue")
    @Expose
    private Integer revenue;
    @SerializedName("runtime")
    @Expose
    private Integer runtime;
    @SerializedName("spoken_languages")
    @Expose
    private List<SpokenLanguage> spokenLanguages = null;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("video")
    @Expose
    private Boolean video;
    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;
    @SerializedName("vote_count")
    @Expose
    private Integer voteCount;
    @SerializedName("videos")
    @Expose
    private Videos videos;
    public final static Parcelable.Creator<MovieDetailResponse> CREATOR = new Creator<MovieDetailResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MovieDetailResponse createFromParcel(Parcel in) {
            MovieDetailResponse instance = new MovieDetailResponse();
            instance.adult = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.backdropPath = ((String) in.readValue((String.class.getClassLoader())));
            instance.collection = ((MovieCollection) in.readValue((MovieCollection.class.getClassLoader())));
            instance.budget = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.genres, (com.example.heiho1.popmovies.model.Genre.class.getClassLoader()));
            instance.homepage = ((String) in.readValue((String.class.getClassLoader())));
            instance.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.imdbId = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalLanguage = ((String) in.readValue((String.class.getClassLoader())));
            instance.originalTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.overview = ((String) in.readValue((String.class.getClassLoader())));
            instance.popularity = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.posterPath = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.productionCompanies, (com.example.heiho1.popmovies.model.ProductionCompany.class.getClassLoader()));
            in.readList(instance.productionCountries, (com.example.heiho1.popmovies.model.ProductionCountry.class.getClassLoader()));
            instance.releaseDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.revenue = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.runtime = ((Integer) in.readValue((Integer.class.getClassLoader())));
            in.readList(instance.spokenLanguages, (com.example.heiho1.popmovies.model.SpokenLanguage.class.getClassLoader()));
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.tagline = ((String) in.readValue((String.class.getClassLoader())));
            instance.title = ((String) in.readValue((String.class.getClassLoader())));
            instance.video = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            instance.voteAverage = ((Double) in.readValue((Double.class.getClassLoader())));
            instance.voteCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.videos = ((Videos) in.readValue((Videos.class.getClassLoader())));
            return instance;
        }

        public MovieDetailResponse[] newArray(int size) {
            return (new MovieDetailResponse[size]);
        }

    };
    private final static long serialVersionUID = 4270282393676043534L;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public MovieDetailResponse withAdult(Boolean adult) {
        this.adult = adult;
        return this;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public MovieDetailResponse withBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
        return this;
    }

    public MovieCollection getCollection() {
        return collection;
    }

    public void setBelongsToCollection(MovieCollection belongsToCollection) {
        this.collection = belongsToCollection;
    }

    public MovieDetailResponse withBelongsToCollection(MovieCollection belongsToCollection) {
        this.collection = belongsToCollection;
        return this;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public MovieDetailResponse withBudget(Integer budget) {
        this.budget = budget;
        return this;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public MovieDetailResponse withGenres(List<Genre> genres) {
        this.genres = genres;
        return this;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public MovieDetailResponse withHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MovieDetailResponse withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public MovieDetailResponse withImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public MovieDetailResponse withOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public MovieDetailResponse withOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public MovieDetailResponse withOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public MovieDetailResponse withPopularity(Double popularity) {
        this.popularity = popularity;
        return this;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public MovieDetailResponse withPosterPath(String posterPath) {
        this.posterPath = posterPath;
        return this;
    }

    public List<ProductionCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public MovieDetailResponse withProductionCompanies(List<ProductionCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
        return this;
    }

    public List<ProductionCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public MovieDetailResponse withProductionCountries(List<ProductionCountry> productionCountries) {
        this.productionCountries = productionCountries;
        return this;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public MovieDetailResponse withReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public MovieDetailResponse withRevenue(Integer revenue) {
        this.revenue = revenue;
        return this;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public MovieDetailResponse withRuntime(Integer runtime) {
        this.runtime = runtime;
        return this;
    }

    public List<SpokenLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public MovieDetailResponse withSpokenLanguages(List<SpokenLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public MovieDetailResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public MovieDetailResponse withTagline(String tagline) {
        this.tagline = tagline;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MovieDetailResponse withTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public MovieDetailResponse withVideo(Boolean video) {
        this.video = video;
        return this;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public MovieDetailResponse withVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public MovieDetailResponse withVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public Videos getVideos() {
        return videos;
    }

    public void setVideos(Videos videos) {
        this.videos = videos;
    }

    public MovieDetailResponse withVideos(Videos videos) {
        this.videos = videos;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(adult).append(backdropPath).append(collection).append(budget).append(genres).append(homepage).append(id).append(imdbId).append(originalLanguage).append(originalTitle).append(overview).append(popularity).append(posterPath).append(productionCompanies).append(productionCountries).append(releaseDate).append(revenue).append(runtime).append(spokenLanguages).append(status).append(tagline).append(title).append(video).append(voteAverage).append(voteCount).append(videos).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof MovieDetailResponse) == false) {
            return false;
        }
        MovieDetailResponse rhs = ((MovieDetailResponse) other);
        return new EqualsBuilder().append(adult, rhs.adult).append(backdropPath, rhs.backdropPath).append(collection, rhs.collection).append(budget, rhs.budget).append(genres, rhs.genres).append(homepage, rhs.homepage).append(id, rhs.id).append(imdbId, rhs.imdbId).append(originalLanguage, rhs.originalLanguage).append(originalTitle, rhs.originalTitle).append(overview, rhs.overview).append(popularity, rhs.popularity).append(posterPath, rhs.posterPath).append(productionCompanies, rhs.productionCompanies).append(productionCountries, rhs.productionCountries).append(releaseDate, rhs.releaseDate).append(revenue, rhs.revenue).append(runtime, rhs.runtime).append(spokenLanguages, rhs.spokenLanguages).append(status, rhs.status).append(tagline, rhs.tagline).append(title, rhs.title).append(video, rhs.video).append(voteAverage, rhs.voteAverage).append(voteCount, rhs.voteCount).append(videos, rhs.videos).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(adult);
        dest.writeValue(backdropPath);
        dest.writeValue(collection);
        dest.writeValue(budget);
        dest.writeList(genres);
        dest.writeValue(homepage);
        dest.writeValue(id);
        dest.writeValue(imdbId);
        dest.writeValue(originalLanguage);
        dest.writeValue(originalTitle);
        dest.writeValue(overview);
        dest.writeValue(popularity);
        dest.writeValue(posterPath);
        dest.writeList(productionCompanies);
        dest.writeList(productionCountries);
        dest.writeValue(releaseDate);
        dest.writeValue(revenue);
        dest.writeValue(runtime);
        dest.writeList(spokenLanguages);
        dest.writeValue(status);
        dest.writeValue(tagline);
        dest.writeValue(title);
        dest.writeValue(video);
        dest.writeValue(voteAverage);
        dest.writeValue(voteCount);
        dest.writeValue(videos);
    }

    public int describeContents() {
        return 0;
    }

}