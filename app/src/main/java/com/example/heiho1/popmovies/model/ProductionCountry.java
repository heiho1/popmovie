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

public class ProductionCountry implements Serializable, Parcelable
{

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("name")
    @Expose
    private String name;
    public final static Parcelable.Creator<ProductionCountry> CREATOR = new Creator<ProductionCountry>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ProductionCountry createFromParcel(Parcel in) {
            ProductionCountry instance = new ProductionCountry();
            instance.iso31661 = ((String) in.readValue((String.class.getClassLoader())));
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ProductionCountry[] newArray(int size) {
            return (new ProductionCountry[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7140694870749382231L;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public ProductionCountry withIso31661(String iso31661) {
        this.iso31661 = iso31661;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductionCountry withName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(iso31661).append(name).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ProductionCountry) == false) {
            return false;
        }
        ProductionCountry rhs = ((ProductionCountry) other);
        return new EqualsBuilder().append(iso31661, rhs.iso31661).append(name, rhs.name).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(iso31661);
        dest.writeValue(name);
    }

    public int describeContents() {
        return 0;
    }

}
