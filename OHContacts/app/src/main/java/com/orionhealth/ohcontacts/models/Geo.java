package com.orionhealth.ohcontacts.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class Geo implements Serializable {

    @SerializedName("lat")
    public String lat;
    @SerializedName("lng")
    public String lng;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Geo geo = (Geo) o;
        return Objects.equal(lat, geo.lat) &&
                Objects.equal(lng, geo.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lat, lng);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("lat", lat)
                .add("lng", lng)
                .toString();
    }
}