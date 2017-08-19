package abdullashaib.social.com.businesslistings.models;

/**
 * Created by user on 4/25/2017.
 */

public class Province {

    public Province() {}
    public Province(String id, String province) {
        this.setId(id);
        this.setProvince(province);
    }

    private String id;
    private String province;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
