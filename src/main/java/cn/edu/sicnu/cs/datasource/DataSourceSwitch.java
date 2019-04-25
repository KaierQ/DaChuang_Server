package cn.edu.sicnu.cs.datasource;

/**
 * 做多线程的本地变量复制处理，隔离数据源
 *
 * @author kaier
 * @date 2019-04-21 18:16
 */
public class DataSourceSwitch {
    /*存储每个线程所需要访问的数据源的名称*/
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dataSourceType
     */
    public static void setDataSourceType(String dataSourceType){
        contextHolder.set(dataSourceType);
    }

    /**
     * 获得数据源
     * @return
     */
    public static String getDataSourceType(){
        return contextHolder.get();
    }

    /**
     * 清空
     */
    public static void clearDataSourceType(){
        contextHolder.remove();
    }

}
