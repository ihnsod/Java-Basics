package com.ihnsod.basics.utils;

import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2019/01/19 18:07
 **/
public class Limit<T> {

    private List<T> list;
    private int size;
    private int segment;
    private int index;

    public Limit(List<T> list, int processors) {
        if (list == null || list.size() < 1 || processors < 1) {
            throw new RuntimeException("集合不能为空");
        }
        this.list = list;
        this.size = list.size();
        this.index = 0;
        this.segment = size / processors;
    }

    public boolean hasLimit() {
        return index < size;
    }

    public List<T> getMore() {
        List<T> ts;
        if (size - index < segment) {
            ts = list.subList(index, size);
            index = size;
        } else {
            ts = list.subList(index, index + segment);
            index += segment;
        }
        return ts;
    }
}
