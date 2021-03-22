package pl.zzpj2021.cleancode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DoIt {

    Map<Integer, Integer> hashMap = new HashMap<>();
    private int maxValue = Integer.MIN_VALUE;
    private int minValue = Integer.MAX_VALUE;

    public DoIt(List<Integer> list) {
        init(list);
    }

    public void init(List<Integer> list) {
        list.forEach(this::init);
    }

    public void init(Integer key) {
        if (hashMap.containsKey(key)) {
            Integer value = hashMap.get(key);
            hashMap.put(key, value + 1);
        } else {
            hashMap.put(1, 1);
        }

        if (key > maxValue) {
            maxValue = key;
        }

        if (key < minValue) {
            minValue = key;
        }
    }

    public int getValueByKey(int i) {
        return hashMap.getOrDefault(i, 0);
    }

    public double calcSumsDivision() {
        double valueSum = 0;
        double keyTimesValueSum = 0;
        for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
            valueSum += entry.getValue();
            keyTimesValueSum += entry.getKey() * entry.getValue();
        }

        return keyTimesValueSum / valueSum;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

}
