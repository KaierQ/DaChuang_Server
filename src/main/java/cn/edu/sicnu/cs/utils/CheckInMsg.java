package cn.edu.sicnu.cs.utils;

/**
 * 上班打卡消息
 * @author Kaier
 */

public enum CheckInMsg {
    /**
     * 打卡成功
     */
    CHECK_IN_SUCCESS("打卡成功",Constances.SUCCESS),
    /**
     * 打卡失败
     */
    CHECK_IN_FAIL("打卡失败",Constances.FAIL);

    private String message;
    private int index;

    CheckInMsg(String message, int index) {
        this.message = message;
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

    public int getIndex() {
        return index;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setIndex(int index) {
        this.index = index;
    }

}

