package cn.edu.sicnu.cs.utils.analogy;

import java.util.List;

/**
 * 求平均值及其平均值的分析结果
 *
 * @author kaier
 * @date 2019-05-14 21:51
 */
public class AverageAnalogy {
    /**
     * 考勤数据集合
     */
    private List<Integer> values;
    /**
     *     存储平均值结果
     */
    private int averageValue;

    public AverageAnalogy() {
        super();
    }

    public AverageAnalogy(List<Integer> values) {
        this.values = values;
    }

    public void computeAverage(){
        int sum=0;
        for (Integer value : values) {
            sum += value;
        }
        averageValue = sum/values.size();
    }

    public int getAverageValue() {
        return averageValue;
    }
}
