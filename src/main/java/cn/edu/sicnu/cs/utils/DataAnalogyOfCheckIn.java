package cn.edu.sicnu.cs.utils;

/**
 *
 * 用于今日上班打卡情况的数据分析结果存储
 * 存储各个时间段的打卡人数,以便View层进行显示
 * @author kaier
 * @date 2019-04-30 10:57
 */
public class DataAnalogyOfCheckIn {

    private int BEFORE_8_00;
    private int _8_00_8_15;
    private int _8_15_8_30;
    private int _8_30_8_45;
    private int _8_45_9_00;

    private int _9_00_9_15;
    private int _9_15_9_30;
    private int AFTER_9_30;

    private int numOfLate;

    private int[][] data;

    public DataAnalogyOfCheckIn() {
        super();
        data = new int[2][5];
    }

    private void addBEFORE_8_00() {
        data[0][0]++;
    }

    private void add_8_00_8_15() {
        data[0][1]++;
    }

    private void add_8_15_8_30() {
        data[0][2]++;
    }

    private void add_8_30_8_45() {
        data[0][3]++;
    }

    private void add_8_45_9_00() {
        data[0][4]++;
    }

    private void add_9_00_9_15() {
        data[1][0]++;
    }

    public void add_9_15_9_30() {
        data[1][1]++;
    }

    public void addAFTER_9_30() {
        data[1][2]++;
    }

    public int getBEFORE_8_00() {
        BEFORE_8_00 = data[0][0];
        return BEFORE_8_00;
    }

    public int get_8_00_8_15() {
        _8_00_8_15 = data[0][1];
        return _8_00_8_15;
    }

    public int get_8_15_8_30() {
        _8_15_8_30 = data[0][2];
        return _8_15_8_30;
    }

    public int get_8_30_8_45() {
        _8_30_8_45 = data[0][3];
        return _8_30_8_45;
    }

    public int get_8_45_9_00() {
        _8_45_9_00 = data[0][4];
        return _8_45_9_00;
    }

    public int get_9_00_9_15() {
        _9_00_9_15 = data[1][0];
        return _9_00_9_15;
    }

    public int get_9_15_9_30() {
        _9_15_9_30 = data[1][1];
        return _9_15_9_30;
    }

    public int getAFTER_9_30() {
        AFTER_9_30 = data[1][2];
        return AFTER_9_30;
    }

    /**
     * 根据时间添加到相应的数据存储单元
     * @param hour
     * @param minute
     * @param second
     */
    public void addData(int hour,int minute,int second){
        int t = minute/15;
        if(hour<=TimeOfWork.TIME_START_WORK-1){
            addBEFORE_8_00();
        }else if (hour<TimeOfWork.TIME_START_WORK){
            addPro(t);
        }else{
            if(t==0){
                //判断是否迟到
                if(second==0){
                    add_8_45_9_00();
                }else{
                    add_9_00_9_15();
                }
            }else if(t==1){
                add_9_15_9_30();
            }else{
                addAFTER_9_30();
            }
        }
    }

    /**
     * 根据分钟存入指定类型单元
     * @param t
     */
    private void addPro(int t){
        switch (t){
            case 0:
                add_8_00_8_15();
                break;
            case 1:
                add_8_15_8_30();
                break;
            case 2:
                add_8_30_8_45();
                break;
            case 3:
                add_8_45_9_00();
                break;
            default:break;
        }
    }

    public int getNumOfLate() {
        numOfLate = get_9_00_9_15()+get_9_15_9_30()+getAFTER_9_30();
        return numOfLate;
    }

}


