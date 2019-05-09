package cn.edu.sicnu.cs.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 一个月上下班打卡的到勤率
 * @author kaier
 * @date 2019-05-09 10:03
 */
public class CheckPercent {

    private List<Integer> percentCheckIn = new ArrayList<>();

    private List<Integer> percentCheckOut = new ArrayList<>();

    public void setPercentCheckIn(List<Integer> percentCheckIn) {
        this.percentCheckIn = percentCheckIn;
    }

    public void setPercentCheckOut(List<Integer> percentCheckOut) {
        this.percentCheckOut = percentCheckOut;
    }

    public List<Integer> getPercentCheckIn() {
        return percentCheckIn;
    }

    public List<Integer> getPercentCheckOut() {
        return percentCheckOut;
    }

}
