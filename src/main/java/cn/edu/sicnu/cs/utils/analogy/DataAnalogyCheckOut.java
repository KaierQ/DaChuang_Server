package cn.edu.sicnu.cs.utils.analogy;

import cn.edu.sicnu.cs.utils.TimeOfWork;

/**
 *
 * 用于今日下班打卡情况的数据分析结果存储
 * 存储各个时间段的打卡人数,以便View层进行显示
 * @author kaier
 * @date 2019-04-30 11:55
 */
public class DataAnalogyCheckOut {
    private int BEFORE_5_00;
    private int _17_00_17_15;
    private int _17_15_17_30;
    private int _17_30_17_45;
    private int AFTER_17_45;

    private int[] data;

    public DataAnalogyCheckOut() {
        super();
        data = new int[5];
    }

    public void addBEFORE_5_00() {
        data[0]++;
    }

    public void add_17_00_17_15() {
        data[1]++;
    }

    public void add_17_15_17_30() {
        data[2]++;
    }

    public void add_17_30_17_45() {
        data[3]++;
    }

    public void addAFTER_17_45() {
        data[4]++;
    }

    public int getBEFORE_5_00() {
        this.BEFORE_5_00 = data[0];
        return BEFORE_5_00;
    }

    public int get_17_00_17_15() {
        this._17_00_17_15 = data[1];
        return _17_00_17_15;
    }

    public int get_17_15_17_30() {
        this._17_15_17_30 = data[2];
        return _17_15_17_30;
    }

    public int get_17_30_17_45() {
        this._17_30_17_45 = data[3];
        return _17_30_17_45;
    }

    public int getAFTER_17_45() {
        this.AFTER_17_45 = data[4];
        return AFTER_17_45;
    }

    /**
     * 根据时间类型判断存入哪个存储单元
     *
     * @param hour
     * @param minute
     * @param second
     */
    public void addData(int hour,int minute,int second){
        if(hour<TimeOfWork.TIME_STOP_WORK){
            addBEFORE_5_00();
        }else if(hour==TimeOfWork.TIME_STOP_WORK){
            int t = minute/15;
            switch (t){
                case 0:
                    add_17_00_17_15();
                    break;
                case 1:
                    add_17_15_17_30();
                    break;
                case 2:
                    add_17_30_17_45();
                    break;
                default:break;
            }
        }else{
            addAFTER_17_45();
        }
    }

}
