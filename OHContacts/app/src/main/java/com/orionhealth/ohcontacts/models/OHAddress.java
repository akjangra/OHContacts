package com.orionhealth.ohcontacts.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class OHAddress implements Serializable {
    @SerializedName("street")
    public String street;
    @SerializedName("suite")
    public String suite;
    @SerializedName("city")
    public String city;
    @SerializedName("zipcode")
    public String zipcode;
    @SerializedName("geo")
    public Geo geo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OHAddress ohAddress = (OHAddress) o;
        return Objects.equal(street, ohAddress.street) &&
                Objects.equal(suite, ohAddress.suite) &&
                Objects.equal(city, ohAddress.city) &&
                Objects.equal(zipcode, ohAddress.zipcode) &&
                Objects.equal(geo, ohAddress.geo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(street, suite, city, zipcode, geo);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("street", street)
                .add("suite", suite)
                .add("city", city)
                .add("zipcode", zipcode)
                .add("geo", geo)
                .toString();
    }
}