package cn.edu.sicnu.cs.utils;

/**
 * 下班打卡消息
 * @author Kaier
 */
public enum CheckOutMsg {
    /**
     * 打卡成功
     */
    CHECK_OUT_SUCCESS("打卡成功",Constances.SUCCESS),
    /**
     * 打卡失败
     */
    CHECK_OUT_FAIL("打卡失败",Constances.FAIL);

    private String message;
    private int index;

    CheckOutMsg(String message, int index) {
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


