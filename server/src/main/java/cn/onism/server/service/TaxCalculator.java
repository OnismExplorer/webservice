package cn.onism.server.service;

/**
 * 税项计算器
 *
 * @author Onism
 * @date 2024/09/30
 */
public class TaxCalculator {

    /**
     *  计算个人所得税的方法
     *
     * @param income 收入
     * @return double
     */
    public double calculateIncomeTax(double income) {
        double taxThreshold = 5000.0; // 起征点
        double taxableIncome = income - taxThreshold;

        if (taxableIncome <= 0) {
            return 0; // 不需缴税
        }

        // 简单的分段计税规则
        if (taxableIncome <= 3000) {
            return taxableIncome * 0.03; // 3% 税率
        } else if (taxableIncome <= 12000) {
            return taxableIncome * 0.1; // 10% 税率
        } else if (taxableIncome <= 25000) {
            return taxableIncome * 0.2; // 20% 税率
        } else if (taxableIncome <= 35000) {
            return taxableIncome * 0.25; // 25% 税率
        } else {
            return taxableIncome * 0.3; // 30% 税率
        }
    }
}
