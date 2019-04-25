package cn.edu.sicnu.cs.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工考勤打卡详细信息
 * @author Kaier
 */
public class AttendanceDetail implements Serializable {
    private static final long serialVersionUID = -2517012293547130019L;

    private Integer id;

    private Integer eId;

    private Date createdDate;

    private Date arriveTime;

    private Date leftTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = arriveTime;
    }

    public Date getLeftTime() {
        return leftTime;
    }

    public void setLeftTime(Date leftTime) {
        this.leftTime = leftTime;
    }

    @Override
    public String toString() {
        return "AttendanceDetail{" +
                "id=" + id +
                ", eId=" + eId +
                ", createdDate=" + createdDate +
                ", arriveTime=" + arriveTime +
                ", leftTime=" + leftTime +
                '}';
    }

}