package cn.edu.sicnu.cs.utils.analogy;

import java.util.List;

/**
 * 方差计算
 *
 * @author kaier
 * @date 2019-05-15 10:21
 */
public class VarianceAnalogy {
    /**
     * 平均值
     */
    private double average;
    /**
     * 原始数据集合
     */
    private List<Integer> values;
    /**
     * 方差的结果值
     */
    private double resultValue;

    public VarianceAnalogy(double average, List<Integer> values) {
        this.average = average;
        this.values = values;
    }

    /**
     * 计算方差
     * @return
     */
    public double computeVariance(){
        double tempSum=0;
        for (Integer value : values) {
            tempSum += Math.pow((value-average),2);
        }
        resultValue = tempSum/(values.size()-1);
        return resultValue;
    }

    /**
     *
     * @return
     */
    public double getResultValue() {
        return resultValue;
    }

}
