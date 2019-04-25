package cn.edu.sicnu.cs.pojo;

import java.io.Serializable;

/**
 *
 * 统计某个员工的出勤情况
 * @author Kaier
 */
public class Attendance implements Serializable {
    private static final long serialVersionUID = -2501942987972410795L;

    private Integer eId;

    private Integer workdays;

    private Integer nolateDays;

    private Integer lateDays;

    private Integer earlyLeftdays;

    private Integer latedLeftdays;

    private Integer monthWorkdays;

    private Integer monthNolateDays;

    private Integer monthLateDays;

    private Integer monthEarlyLeftdays;

    private Integer monthLatedLeftdays;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public Integer getWorkdays() {
        return workdays;
    }

    public void setWorkdays(Integer workdays) {
        this.workdays = workdays;
    }

    public Integer getNolateDays() {
        return nolateDays;
    }

    public void setNolateDays(Integer nolateDays) {
        this.nolateDays = nolateDays;
    }

    public Integer getLateDays() {
        return lateDays;
    }

    public void setLateDays(Integer lateDays) {
        this.lateDays = lateDays;
    }

    public Integer getEarlyLeftdays() {
        return earlyLeftdays;
    }

    public void setEarlyLeftdays(Integer earlyLeftdays) {
        this.earlyLeftdays = earlyLeftdays;
    }

    public Integer getLatedLeftdays() {
        return latedLeftdays;
    }

    public void setLatedLeftdays(Integer latedLeftdays) {
        this.latedLeftdays = latedLeftdays;
    }

    public Integer getMonthWorkdays() {
        return monthWorkdays;
    }

    public void setMonthWorkdays(Integer monthWorkdays) {
        this.monthWorkdays = monthWorkdays;
    }

    public Integer getMonthNolateDays() {
        return monthNolateDays;
    }

    public void setMonthNolateDays(Integer monthNolateDays) {
        this.monthNolateDays = monthNolateDays;
    }

    public Integer getMonthLateDays() {
        return monthLateDays;
    }

    public void setMonthLateDays(Integer monthLateDays) {
        this.monthLateDays = monthLateDays;
    }

    public Integer getMonthEarlyLeftdays() {
        return monthEarlyLeftdays;
    }

    public void setMonthEarlyLeftdays(Integer monthEarlyLeftdays) {
        this.monthEarlyLeftdays = monthEarlyLeftdays;
    }

    public Integer getMonthLatedLeftdays() {
        return monthLatedLeftdays;
    }

    public void setMonthLatedLeftdays(Integer monthLatedLeftdays) {
        this.monthLatedLeftdays = monthLatedLeftdays;
    }


    @Override
    public String toString() {
        return "Attendance{" +
                "eId=" + eId +
                ", workdays=" + workdays +
                ", nolateDays=" + nolateDays +
                ", lateDays=" + lateDays +
                ", earlyLeftdays=" + earlyLeftdays +
                ", latedLeftdays=" + latedLeftdays +
                ", monthWorkdays=" + monthWorkdays +
                ", monthNolateDays=" + monthNolateDays +
                ", monthLateDays=" + monthLateDays +
                ", monthEarlyLeftdays=" + monthEarlyLeftdays +
                ", monthLatedLeftdays=" + monthLatedLeftdays +
                '}';
    }

}