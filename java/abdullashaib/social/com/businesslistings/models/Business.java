package abdullashaib.social.com.businesslistings.models;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by user on 4/25/2017.
 */

public class Business implements Serializable {

    public String id;
    public String businessname;
    public String description;
    public String logo;
    public String logoPath;
    public String website;
    public String email;
    public String phonenumber;
    public String address;
    public String city;
    public String state;
    public String postalcode;
    public String country;
    public String addDate;
    public int status;

    public Business() {}


    public Business(String id, String businessname, String description, String logo, String website, String email, String phonenumber, String address, String city, String state, String postalcode, String country) {
        this.setId(id);
        this.setBusinessname(businessname);
        this.setDescription(description);
        this.setLogo(logo);
        this.setWebsite(website);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setPostalcode(postalcode);
        this.setCountry(country);
    }

    public Business(String businessname, String description, String logo, String website, String email, String phonenumber, String address, String city, String state, String postalcode, String country) {
        this.setBusinessname(businessname);
        this.setDescription(description);
        this.setLogo(logo);
        this.setWebsite(website);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setPostalcode(postalcode);
        this.setCountry(country);
    }

    public Business(String businessname, String description, String logo, String logoPath, String website, String email, String phonenumber, String address, String city, String state, String postalcode, String country, String adddate, int status) {
        this.setBusinessname(businessname);
        this.setDescription(description);
        this.setLogo(logo);
        this.setLogoPath(logoPath);
        this.setWebsite(website);
        this.setEmail(email);
        this.setPhonenumber(phonenumber);
        this.setAddress(address);
        this.setCity(city);
        this.setState(state);
        this.setPostalcode(postalcode);
        this.setCountry(country);
        this.setAddDate(adddate);
        this.setStatus(status);
    }

    public Business(String id, int syncStatus) {
        this.setId(id);
        this.setStatus(syncStatus);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBusinessname() {
        return businessname;
    }

    public void setBusinessname(String businessname) {
        this.businessname = businessname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
