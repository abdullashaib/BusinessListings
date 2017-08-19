package abdullashaib.social.com.businesslistings.models;

/**
 * Created by user on 4/25/2017.
 */

public class Profile {

    public int profileid;
    public String jobTitle;
    public String firstname;
    public String lastname;
    public String mission;
    public String skill1;
    public String skill2;
    public String skill3;
    public String skill4;
    public String skill5;
    public String education1;
    public String education2;
    public String education3;
    public String achievement;
    public String phone;
    public String emailaddress;
    public String imageLink;
    public String imagePath;
    public String addDate;
    public int status;


    public Profile() {

    }

    public Profile(int profileid, String jobTitle, String firstname, String lastname, String mission, String skill1, String skill2, String skill3, String skill4, String skill5,
                   String education1, String education2, String education3, String achievement, String phone, String emailaddress, String imageLink) {

        this.setProfileid(profileid);
        this.setJobTitle(jobTitle);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setMission(mission);
        this.setSkill1(skill1);
        this.setSkill2(skill2);
        this.setSkill3(skill3);
        this.setSkill4(skill4);
        this.setSkill5(skill5);
        this.setEducation1(education1);
        this.setEducation2(education2);
        this.setEducation3(education3);
        this.setAchievement(achievement);
        this.setPhone(phone);
        this.setEmailaddress(emailaddress);
        this.setImageLink(imageLink);

    }

    public Profile(int profileid, String jobTitle, String firstname, String lastname, String mission, String skill1, String skill2, String skill3, String skill4, String skill5,
                    String education1, String education2, String education3, String achievement, String phone, String emailaddress, String imageLink, String imagePath, String adddate, int syncStatus) {

        this.setProfileid(profileid);
        this.setJobTitle(jobTitle);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setMission(mission);
        this.setSkill1(skill1);
        this.setSkill2(skill2);
        this.setSkill3(skill3);
        this.setSkill4(skill4);
        this.setSkill5(skill5);
        this.setEducation1(education1);
        this.setEducation2(education2);
        this.setEducation3(education3);
        this.setAchievement(achievement);
        this.setPhone(phone);
        this.setEmailaddress(emailaddress);
        this.setImageLink(imageLink);
        this.setImagePath(imagePath);
        this.setAddDate(adddate);
        this.setStatus(syncStatus);

    }

    public Profile(int profileid, String firstname, String lastname, String mission, String imageLink) {

        this.setProfileid(profileid);
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setMission(mission);
        this.setImageLink(imageLink);

    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
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

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public String getSkill1() {
        return skill1;
    }

    public void setSkill1(String skill1) {
        this.skill1 = skill1;
    }

    public String getSkill2() {
        return skill2;
    }

    public void setSkill2(String skill2) {
        this.skill2 = skill2;
    }

    public String getSkill3() {
        return skill3;
    }

    public void setSkill3(String skill3) {
        this.skill3 = skill3;
    }

    public String getSkill4() {
        return skill4;
    }

    public void setSkill4(String skill4) {
        this.skill4 = skill4;
    }

    public String getSkill5() {
        return skill5;
    }

    public void setSkill5(String skill5) {
        this.skill5 = skill5;
    }

    public String getEducation1() {
        return education1;
    }

    public void setEducation1(String education1) {
        this.education1 = education1;
    }

    public String getEducation2() {
        return education2;
    }

    public void setEducation2(String education2) {
        this.education2 = education2;
    }

    public String getEducation3() {
        return education3;
    }

    public void setEducation3(String getEducation3) {
        this.education3 = getEducation3;
    }

    public String getAchievement() {
        return achievement;
    }

    public void setAchievement(String achievement) {
        this.achievement = achievement;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }


    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
