package abdullashaib.social.com.businesslistings.models;

import java.io.Serializable;

/**
 * Created by user on 4/25/2017.
 */

public class Posts implements Serializable {

    private String postId;
    private String title;
    private String description;
    private String firstname;
    private String lastname;
    private String emaail;
    private int userId;
    private String postdate;
    private int status;


    public Posts() {}

    public Posts(String title, String emaail) {
        this.setTitle(title);
        this.setEmaail(emaail);
    }

    public Posts(String postId, int syncstatus) {
        this.setPostId(postId);
        this.setStatus(syncstatus);
    }


    public Posts(String title, String desc, String firstname, String lastname, String postdate) {

        this.setTitle(title);
        this.setDescription(desc);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPostdate(postdate);
    }

    public Posts(String title, String desc, String email, String firstname, String lastname, String postdate, int status) {

        this.setTitle(title);
        this.setDescription(desc);
        this.setEmaail(email);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPostdate(postdate);
        this.setStatus(status);
    }

    public Posts(String title, String desc, String email, String firstname, String lastname, String postdate) {

        this.setTitle(title);
        this.setDescription(desc);
        this.setEmaail(email);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPostdate(postdate);

    }

    public Posts(String postId, String title, String desc) {
        this.setTitle(title);
        this.setDescription(desc);
        this.setPostId(postId);
    }


    public Posts(String postId, String title, String desc, String email, String firstname, String lastname, String postdate) {

        this.setTitle(title);
        this.setDescription(desc);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPostdate(postdate);
        this.setPostId(postId);
        this.setEmaail(email);
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPostId() {
        return postId;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmaail() {
        return emaail;
    }

    public void setEmaail(String emaail) {
        this.emaail = emaail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
