package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.HashSet;

/**
 * @author Ihnsod
 * @create 2018-05-16 16:17
 **/
public class HashSetExample {

    /**
     * hashset
     */
    @Test
    public void test(){
        HashSet<String> hashSet = new HashSet<>();
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("heh");
        hashSet.add(null);
        hashSet.addAll(hashSet1);
    }

}
