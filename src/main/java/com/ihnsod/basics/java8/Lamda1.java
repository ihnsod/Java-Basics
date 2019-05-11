package com.ihnsod.basics.java8;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

/**
 * @author: Ihnsod
 * @create: 2018/12/02 02:10
 **/
public class Lamda1 {

    public static void main(String[] args) {

        User user1 = new User(22, "zxx");
        User user2 = new User(21, "wz");
        User user3 = new User(63, "hehe");
        User user4 = new User(245, "zdf");
        User user5 = new User(26, "agl");
        User user6 = new User(75, "zg");
        User user7 = new User(null, "hdg");
        User user8 = new User(88, "yyqx");
        User user9 = new User(22, null);

        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);


        /**
         * List -> Map
         *  需要注意的是：
         *  toMap 如果集合对象有重复的key，会报错Duplicate key ....
         *  可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
         */
        Map<String, User> collect = users.stream().collect(Collectors.toMap(User::getName, user -> user, (u1, u2) -> u1));

        System.err.println(collect);

        //有null值得排序方式 把null值放到最后
        Collections.sort(users, comparing(User::getAge, nullsLast(naturalOrder())));
        //有null值得排序  把null 值放到最前面
        Collections.sort(users, comparing(User::getAge, nullsFirst(naturalOrder())));


        System.err.println(users);

        HashMap<String, User> hashMap = new HashMap<>();

        hashMap.put(user1.getName(), user1);
        hashMap.put(user2.getName(), user2);
        hashMap.put(user3.getName(), user3);
        hashMap.put(user4.getName(), user4);
        hashMap.put(user5.getName(), user5);
        hashMap.put(user6.getName(), user6);
        hashMap.put(user7.getName(), user7);
        hashMap.put(user8.getName(), user8);
        hashMap.put(user9.getName(), user9);


        //正常排序
//        LinkedHashMap<String, User> collectKey = hashMap.entrySet().stream().sorted
//                (Map.Entry.comparingByKey()).collect(Collectors.toMap(Map.Entry::getKey,
//                Map.Entry::getValue, (olderValue, newValue) -> olderValue, LinkedHashMap::new));


//        System.err.println(collectKey);

        //排序的值有null的处理
        LinkedHashMap<String, User> collectNullKey = hashMap.entrySet().stream().sorted
                (Map.Entry.comparingByKey(nullsLast(naturalOrder())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue
                        , (olderValue, newValue) -> olderValue, LinkedHashMap::new));


        System.err.println(collectNullKey);


        //正常的根据value的排序
//        LinkedHashMap<String, User> collectValue = hashMap.entrySet().stream().sorted
//                (Map.Entry.comparingByValue(Comparator.comparing(User::getAge))).collect
//                (Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//
//        System.err.println(collectValue);

        //序的值有null的处理
        LinkedHashMap<String, User> collectNullValue = hashMap.entrySet().stream().sorted
                (Map.Entry.comparingByValue(comparing(User::getAge, nullsLast(naturalOrder())))).collect
                (Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        System.err.println(collectNullValue);

    }
}
