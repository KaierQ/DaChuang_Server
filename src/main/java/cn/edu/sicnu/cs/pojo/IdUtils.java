package cn.edu.sicnu.cs.pojo;

import java.io.Serializable;

/**
 * 员工ID实体类
 * @author Kaier
 */
public class IdUtils implements Serializable {
    private static final long serialVersionUID = -5885156795330002940L;

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}