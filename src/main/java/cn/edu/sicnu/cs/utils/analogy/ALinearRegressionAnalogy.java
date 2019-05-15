package cn.edu.sicnu.cs.utils.analogy;

import java.util.List;

/**
 * 一元线性回归方程生成分析
 *
 * @author kaier
 * @date 2019-05-15 10:42
 */
public class ALinearRegressionAnalogy {
    /**
     * 原始数据集合
     */
    private List<Integer> values;
    /**
     * 回归分析曲线斜率
     */
    private double a;
    /**
     * 回归分析曲线的截距
     */
    private double b;
    /**
     * 平均值
     */
    private double averageY;

    public ALinearRegressionAnalogy(double averageY,List<Integer> values) {
        this.values = values;
        this.averageY = averageY;
    }

    /**
     * 计算线性回归分析曲线的斜率和截距
     */
    public void computeResult(){
        int size = values.size();
        double averageX=(1+size)/2;
        double sumTop=0;
        double sumBottom=0;

        for (int i=0;i<size;i++){
            sumTop += (i-averageX)*(values.get(i)-averageY);
            sumBottom += Math.pow((i-averageX),2);
        }
        b = sumTop/sumBottom;
        a = averageY-b*averageX;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

}
