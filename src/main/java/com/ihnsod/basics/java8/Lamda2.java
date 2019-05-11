package com.ihnsod.basics.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Ihnsod
 * @create: 2018/12/21 00:00
 **/
public class Lamda2 {
    @Test
    public void test() {
        List<Item> items = new ArrayList<>(3);
        items.add(new Item(1, 2, 3));
        items.add(new Item(2, 4, 5));
        items.add(new Item(3, 5, 6));

        Integer integer = items.stream().map(item -> item.getCount() * item.getPrice()).reduce((x, y) -> x + y).get();

        System.err.println(integer);
    }

    @Data
    @AllArgsConstructor
    class Item {
        Integer sku;
        Integer count;
        Integer price;
    }
}
