package cn.edu.sicnu.cs.pojo;

/**
 * @author kaier
 * @date 2019-04-26 21:19
 */
public class ManagerLoginCheckBean {
    private Integer cId;
    private String password;

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getcId() {
        return cId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
