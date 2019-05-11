package com.ihnsod.basics.collection;

import org.junit.Test;

import java.util.EnumSet;

/**
 * @author: Ihnsod
 * @create: 2019/05/01 17:00
 **/
public class EnumSetExample {
    @Test
    public void structure() {
        // 构造方法为一个枚举类
        EnumSet<Week> weeks = EnumSet.allOf(Week.class);
        // 构造完成后里枚举数据为自己定义的枚举的顺序
        System.out.println(weeks);
        // 添加一个已经存在的枚举值 依旧会保持不重复
        weeks.add(Week.Monday);
        System.out.println(weeks);

        // 空参构造方法
        EnumSet<Week> noneOf = EnumSet.noneOf(Week.class);
        noneOf.add(Week.Monday);
        noneOf.add(Week.Thursday);
        noneOf.add(Week.Tuesday);
        noneOf.add(Week.Sunday);
        noneOf.add(Week.Friday);
        noneOf.add(Week.Saturday);
        // 无论添加数据的顺序怎样 都会根据枚举类中的顺序来排列
        System.out.println(noneOf);

        // 根据另一个枚举set进行构造
        EnumSet<Week> weeks1 = EnumSet.complementOf(weeks);
        System.out.println(weeks1);
    }

    enum Week {
        Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday
    }

    @Test
    public void test1() {
        EnumSet<Week> enumSet = EnumSet.allOf(Week.class);
        System.out.println(enumSet);
        EnumSet<Week> enumSet1 = EnumSet.noneOf(Week.class);
        enumSet1.add(Week.Monday);
        enumSet1.add(Week.Thursday);
        System.out.println(enumSet1);
        EnumSet<Week> enumSet2 = EnumSet.complementOf(enumSet1);
        System.out.println(enumSet2);
    }
}
