package com.jarvisframework.tool.core.comparator;

import java.io.Serializable;
import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * <p>按照GBK拼音顺序对给定的汉字字符串排序</p>
 *
 * @author 王涛
 * @since 1.0, 2020-07-30 11:27:12
 */
public class PinyinComparator implements Comparator<String>, Serializable {

    private static final long serialVersionUID = 1L;

    final Collator collator;

    /**
     * 构造
     */
    public PinyinComparator() {
        collator = Collator.getInstance(Locale.CHINESE);
    }

    @Override
    public int compare(String o1, String o2) {
        return collator.compare(o1, o2);
    }

}
