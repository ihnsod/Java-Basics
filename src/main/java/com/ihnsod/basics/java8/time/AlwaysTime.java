package com.ihnsod.basics.java8.time;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author: Ihnsod
 * @create: 2019/04/27 22:37
 **/
public class AlwaysTime {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        /**
         * 获取当前时间
         */
        LocalDate now = LocalDate.now();
        /**
         * 当天开始时间
         */
        LocalDateTime todayStart = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        System.err.println("当天开始时间" + dateTimeFormatter.format(todayStart));
        /**
         * 当天结束时间
         */
        LocalDateTime todayEnd = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        System.err.println("当天结束时间" + dateTimeFormatter.format(todayEnd));
        /**
         * 本周开始时间
         */
        TemporalAdjuster firstOfWeek = TemporalAdjusters.ofDateAdjuster(localDate ->
                localDate.minusDays(localDate.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue()));
        System.err.println("本周开始时间" + dateTimeFormatter.format(LocalDateTime.of(now.with(firstOfWeek), LocalTime.MIN)));
        /**
         *  本周结束时间
         */
        TemporalAdjuster lastOfWeek = TemporalAdjusters.ofDateAdjuster(localDate ->
                localDate.plusDays(DayOfWeek.SUNDAY.getValue() - localDate.getDayOfWeek().getValue()));
        System.err.println("本周结束时间" + dateTimeFormatter.format(LocalDateTime.of(now.with(lastOfWeek), LocalTime.MAX)));
        /**
         *  本月的第一天
         */
        String monthStart = formatter.format(LocalDate.of(now.getYear(), now.getMonth(), 1));
        System.err.println("本月第一天" + monthStart);
        /**
         *  本月最后一天
         */
        String monthEnd = formatter.format(now.with(TemporalAdjusters.lastDayOfMonth()));
        System.err.println("本月最后一天" + monthEnd);

        String s = "iisf";
        int i = s.hashCode();
    }
}
