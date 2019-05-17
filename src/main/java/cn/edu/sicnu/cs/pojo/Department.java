package cn.edu.sicnu.cs.pojo;

/**
 * 职能部门信息实体类
 * @author Kaier
 */
public class Department {
    private Integer id;

    private String name;

    private Integer leaderId;

    private String leaderName;

    private Integer personnelNum;

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

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName == null ? null : leaderName.trim();
    }

    public Integer getPersonnelNum() {
        return personnelNum;
    }

    public void setPersonnelNum(Integer personnelNum) {
        this.personnelNum = personnelNum;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", leaderId=" + leaderId +
                ", leaderName='" + leaderName + '\'' +
                ", personnelNum=" + personnelNum +
                '}';
    }

}