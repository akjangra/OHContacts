package com.orionhealth.ohcontacts.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class Company implements Serializable {

    @SerializedName("name")
    public String name;
    @SerializedName("catchPhrase")
    public String catchPhrase;
    @SerializedName("bs")
    public String bs;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equal(name, company.name) &&
                Objects.equal(catchPhrase, company.catchPhrase) &&
                Objects.equal(bs, company.bs);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, catchPhrase, bs);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("name", name)
                .add("catchPhrase", catchPhrase)
                .add("bs", bs)
                .toString();
    }
}