package cn.edu.sicnu.cs.utils;

/**
 * 上下班规定时间
 * @author kaier
 * @date 2019-04-23 13:07
 */
public class TimeOfWork {

    /**
     * 上班时间
     */
    public static final int TIME_START_WORK = 9;
    /**
     * 下班时间
     */
    public static final int TIME_STOP_WORK = 17;

    /**
     * 上班之前(00:00~TIME_START_WORK之间)
     */
    public static final int BRFORE_WORK = 1;
    /**
     * 工作时间段(TIME_START_WORK~TIME_STOP_WORK之间)
     */
    public static final int ON_WORK = 2;
    /**
     * 已经到了下班时间(TIME_STOP_WORK~24:00之间)
     */
    public static final int AFTER_WORK = 3;

}
