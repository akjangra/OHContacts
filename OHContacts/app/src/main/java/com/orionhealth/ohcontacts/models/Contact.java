package com.orionhealth.ohcontacts.models;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by ashokjangra70@gmail.com on 11/06/16.
 */
public class Contact implements Serializable, Comparable{
    @SerializedName("id")
    public String id;
    @SerializedName("name")
    public String name;
    @SerializedName("username")
    public String username;
    @SerializedName("email")
    public String email;
    @SerializedName("address")
    public OHAddress ohAddress;
    @SerializedName("phone")
    public String phone;
    @SerializedName("website")
    public String website;
    @SerializedName("company")
    public Company company;

    public Contact(String name, String username, String email, String website) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.website = website;
    }

/*    @Override
    public int compare(Object lhs, Object rhs) {
        return (((Contact)lhs).name.compareTo(((Contact)rhs).name));
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equal(id, contact.id) &&
                Objects.equal(name, contact.name) &&
                Objects.equal(username, contact.username) &&
                Objects.equal(email, contact.email) &&
                Objects.equal(ohAddress, contact.ohAddress) &&
                Objects.equal(phone, contact.phone) &&
                Objects.equal(website, contact.website) &&
                Objects.equal(company, contact.company);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, username, email, ohAddress, phone, website, company);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("username", username)
                .add("email", email)
                .add("ohAddress", ohAddress)
                .add("phone", phone)
                .add("website", website)
                .add("company", company)
                .toString();
    }

    @Override
    public int compareTo(Object another) {
        return this.name.compareTo(((Contact)another).name);
    }
}