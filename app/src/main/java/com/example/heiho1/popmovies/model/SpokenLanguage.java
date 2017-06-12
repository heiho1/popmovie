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
 * Created by heiho1 on 6/9/17.
 */

public class SpokenLanguage implements Serializable, Parcelable
{

    @SerializedName("iso_639_1")
    @Expose
    private String iso6391;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public SpokenLanguage createFromParcel(Parcel in) {
            SpokenLanguage instance = new SpokenLanguage();
            instance.iso6391 = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public SpokenLanguage[] newArray(int size) {
            return (new SpokenLanguage[size]);
        }

    }
            ;
    private final static long serialVersionUID = -5004037067780907513L;

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public SpokenLanguage withIso6391(String iso6391) {
        this.iso6391 = iso6391;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SpokenLanguage withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(iso6391).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SpokenLanguage) == false) {
            return false;
        }
        SpokenLanguage rhs = ((SpokenLanguage) other);
        return new EqualsBuilder().append(iso6391, rhs.iso6391).append(name, rhs.name).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iso6391);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}
