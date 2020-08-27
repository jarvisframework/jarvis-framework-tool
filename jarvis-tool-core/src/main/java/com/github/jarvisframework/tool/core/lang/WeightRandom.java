package com.github.jarvisframework.tool.core.lang;

import com.github.jarvisframework.tool.core.collection.CollectionUtils;
import com.github.jarvisframework.tool.core.map.MapUtils;
import com.github.jarvisframework.tool.core.util.RandomUtils;

import java.io.Serializable;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>description</p>
 *
 * @param <T> 权重随机获取的对象类型
 * @author 王涛
 * @see <a href="https://www.cnblogs.com/waterystone/p/5708063.html">参考博客</a>
 * @since 1.0, 2020-07-23 12:11:46
 */
public class WeightRandom<T> implements Serializable {

    private static final long serialVersionUID = -8244697995702786499L;

    private final TreeMap<Double, T> weightMap;

    private final Random random;

    /**
     * 创建权重随机获取器
     *
     * @param <T> 权重随机获取的对象类型
     * @return {@link WeightRandom}
     */
    public static <T> WeightRandom<T> create() {
        return new WeightRandom<>();
    }

    // ---------------------------------------------------------------------------------- Constructor start

    /**
     * 构造
     */
    public WeightRandom() {
        weightMap = new TreeMap<>();
        random = RandomUtils.getRandom();
    }

    /**
     * 构造
     *
     * @param weightObj 带有权重的对象
     */
    public WeightRandom(WeightObj<T> weightObj) {
        this();
        if (null != weightObj) {
            add(weightObj);
        }
    }

    /**
     * 构造
     *
     * @param weightObjs 带有权重的对象
     */
    public WeightRandom(Iterable<WeightObj<T>> weightObjs) {
        this();
        if (CollectionUtils.isNotEmpty(weightObjs)) {
            for (WeightObj<T> weightObj : weightObjs) {
                add(weightObj);
            }
        }
    }

    /**
     * 构造
     *
     * @param weightObjs 带有权重的对象
     */
    public WeightRandom(WeightObj<T>[] weightObjs) {
        this();
        for (WeightObj<T> weightObj : weightObjs) {
            add(weightObj);
        }
    }
    // ---------------------------------------------------------------------------------- Constructor end

    /**
     * 增加对象
     *
     * @param obj    对象
     * @param weight 权重
     * @return this
     */
    public WeightRandom<T> add(T obj, double weight) {
        return add(new WeightObj<>(obj, weight));
    }

    /**
     * 增加对象权重
     *
     * @param weightObj 权重对象
     * @return this
     */
    public WeightRandom<T> add(WeightObj<T> weightObj) {
        if (null != weightObj) {
            final double weight = weightObj.getWeight();
            if (weightObj.getWeight() > 0) {
                double lastWeight = (this.weightMap.size() == 0) ? 0 : this.weightMap.lastKey();
                // 权重累加
                this.weightMap.put(weight + lastWeight, weightObj.getObj());
            }
        }
        return this;
    }

    /**
     * 清空权重表
     *
     * @return this
     */
    public WeightRandom<T> clear() {
        if (null != this.weightMap) {
            this.weightMap.clear();
        }
        return this;
    }

    /**
     * 下一个随机对象
     *
     * @return 随机对象
     */
    public T next() {
        if (MapUtils.isEmpty(this.weightMap)) {
            return null;
        }
        final double randomWeight = this.weightMap.lastKey() * random.nextDouble();
        final SortedMap<Double, T> tailMap = this.weightMap.tailMap(randomWeight, false);
        return this.weightMap.get(tailMap.firstKey());
    }

    /**
     * 带有权重的对象包装
     *
     * @param <T> 对象类型
     * @author looly
     */
    public static class WeightObj<T> {
        /**
         * 对象
         */
        private T obj;
        /**
         * 权重
         */
        private final double weight;

        /**
         * 构造
         *
         * @param obj    对象
         * @param weight 权重
         */
        public WeightObj(T obj, double weight) {
            this.obj = obj;
            this.weight = weight;
        }

        /**
         * 获取对象
         *
         * @return 对象
         */
        public T getObj() {
            return obj;
        }

        /**
         * 设置对象
         *
         * @param obj 对象
         */
        public void setObj(T obj) {
            this.obj = obj;
        }

        /**
         * 获取权重
         *
         * @return 权重
         */
        public double getWeight() {
            return weight;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((obj == null) ? 0 : obj.hashCode());
            long temp;
            temp = Double.doubleToLongBits(weight);
            result = prime * result + (int) (temp ^ (temp >>> 32));
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            WeightObj<?> other = (WeightObj<?>) obj;
            if (this.obj == null) {
                if (other.obj != null) {
                    return false;
                }
            } else if (!this.obj.equals(other.obj)) {
                return false;
            }
            return Double.doubleToLongBits(weight) == Double.doubleToLongBits(other.weight);
        }
    }

}
