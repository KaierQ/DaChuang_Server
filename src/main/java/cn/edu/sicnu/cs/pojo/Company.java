package cn.edu.sicnu.cs.pojo;

/**
 *  公司信息实体类
 * @author Kaier
 */
public class Company {

    private Integer cId;

    private String companyName;

    private String ceoName;

    private String ceoId;

    private String username;

    private String password;

    private String sysCheckPassword;

    private String intro;

    private String tel;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCeoName() {
        return ceoName;
    }

    public void setCeoName(String ceoName) {
        this.ceoName = ceoName == null ? null : ceoName.trim();
    }

    public String getCeoId() {
        return ceoId;
    }

    public void setCeoId(String ceoId) {
        this.ceoId = ceoId == null ? null : ceoId.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSysCheckPassword() {
        return sysCheckPassword;
    }

    public void setSysCheckPassword(String sysCheckPassword) {
        this.sysCheckPassword = sysCheckPassword == null ? null : sysCheckPassword.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    @Override
    public String toString() {
        return "Company{" +
                "cId=" + cId +
                ", companyName='" + companyName + '\'' +
                ", ceoName='" + ceoName + '\'' +
                ", ceoId='" + ceoId + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sysCheckPassword='" + sysCheckPassword + '\'' +
                ", intro='" + intro + '\'' +
                ", tel='" + tel + '\'' +
                '}';
    }
}