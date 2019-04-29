package cn.edu.sicnu.cs.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * 员工实体类
 * @author Kaier
 */
public class Employee implements Serializable {
    private static final long serialVersionUID = 3855845118559432313L;

    private Integer id;

    private String name;

    private String title;

    private BigDecimal salary;

    private Date signDate;

    private Integer departmentId;

    private byte[] image;

    private String date;

    public String getDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(signDate);
        date = calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        return date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        signDate.getDate();
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", salary=" + salary +
                ", signDate=" + signDate +
                ", departmentId=" + departmentId +
                '}';
    }

}