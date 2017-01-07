package com.javaapi.test.arithmetic.quchong.quanzhong2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 */
public class WeightUtils {
    /**
     * 根据权重列表随机选出一个来源,权重大的出现的概率高
     *
     * @param list
     * @return
     */
    public static Weight choose(List<Weight> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        Integer weightSum = 0;
        for (Weight wc : list) {
            weightSum += wc.getWeight();
        }

        if (weightSum <= 0) {
            return null;
        }
        Random random = new Random();
        Integer n = random.nextInt(weightSum) + 1; // n in [1, weightSum]
        Integer m = 0;
        for (Weight wc : list) {
            if (m <= n && n <= m + wc.getWeight()) {
                return wc;
            }
            m += wc.getWeight();
        }
        return null;
    }

    public static void main(String[] args) {
        List<Weight> list = new ArrayList<>();
        Weight e = new Weight();
        e.setName("虚拟奖品");
        e.setWeight(10);
        Weight e2= new Weight();
        e2.setName("实际奖品");
        e2.setWeight(5);
        list.add(e);
        list.add(e2);
        Weight choose = choose(list);
        System.out.println("choose = " + choose);

    }
}
