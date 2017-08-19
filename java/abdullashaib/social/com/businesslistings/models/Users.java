package abdullashaib.social.com.businesslistings.models;

/**
 * Created by user on 4/25/2017.
 */

public class Users {

    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private String accounttyple;
    private int id;
    private int status;
    private String registerDate;

    public Users() {}

    public Users(String firstname, String lastname, String phone, String email, String password, String accountType, String regdate, int syncStatus) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPhone(phone);
        this.setEmail(email);
        this.setPassword(password);
        this.setAccounttyple(accountType);
        this.setRegisterDate(regdate);
        this.setStatus(syncStatus);
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccounttyple() {
        return accounttyple;
    }

    public void setAccounttyple(String accounttyple) {
        this.accounttyple = accounttyple;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
