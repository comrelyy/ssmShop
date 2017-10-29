package cn.relyy.shop.vo;

/**
 * Created by relyy on 2017/7/7.
 */
public class UserVo {

    private String userName;
    private String password;
    private String realName;
    private String gender;
    private String email;
    private String phoneNum;
    private String address;
    private String userStage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserStage() {
        return userStage;
    }

    public void setUserStage(String userStage) {
        this.userStage = userStage;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", address='" + address + '\'' +
                ", userStage='" + userStage + '\'' +
                '}';
    }
}
