package cn.edu.sicnu.cs.pojo;

import java.util.Date;

/**
 *
 * 用于存储某员工今日打卡信息
 *
 * @author kaier
 * @date 2019-04-29 14:30
 */
public class EmployeeTodayDetail {

    private Integer eId;

    private String name;

    private String title;

    private Date arriveTime;

    private Date leftTime;

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void setLeftTime(Date leftTime) {
        this.leftTime = leftTime;
    }

    public Integer geteId() {
        return eId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public Date getLeftTime() {
        return leftTime;
    }

    @Override
    public String toString() {
        return "EmployeeTodayDetail{" +
                "eId=" + eId +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", arriveTime=" + arriveTime +
                ", leftTime=" + leftTime +
                '}';
    }

}
