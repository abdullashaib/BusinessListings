package abdullashaib.social.com.businesslistings.models;

/**
 * Created by user on 4/25/2017.
 */

public class Country {

    private String id;
    private String country;

    public Country(String id, String country) {
        this.setId(id);
        this.setCountry(country);
    }

    public Country() {}
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
