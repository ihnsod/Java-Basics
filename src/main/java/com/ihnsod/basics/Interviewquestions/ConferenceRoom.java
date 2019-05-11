package com.ihnsod.basics.Interviewquestions;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

/**
 * @author: Ihnsod
 * @create: 2019/01/06 21:00
 * 多人预定会议室功能的简单实现思路
 **/
public class ConferenceRoom {

    private ReentrantLock lock = new ReentrantLock();

    // 把一天24小时分为 48份  索引从 0 开始到 47 结束
    private static final Integer BEGIN_SIGN = 0;
    private static final Integer END_SIGN = 47;

    /**
     * 前端选好时间段后进行更新数据库
     */
    @Test
    public void Reserve() {
        //根据从前端传过来的时间去HashMap中查找
        Integer beginSign = 12;
        Integer endSign = 17;

        lock.lock();
        //时间段冲突比对
        getList().forEach(conference -> {
            if (conference.getBeginSign() <= beginSign && conference.getEndSign() >= beginSign
                    || conference.getBeginSign() <= endSign && conference.getEndSign() >= endSign
                    || conference.getBeginSign() < beginSign && conference.getEndSign() > endSign
                    || conference.getEndSign() > beginSign && conference.getEndSign() < endSign) {
                return;
            }
        });

        //查询此时间段 version
        //更新数据库 根据version更新 并把version + 1

        //释放锁
        lock.unlock();
    }

    /**
     * 前端显示的某个会议室的空闲时间段
     */
    @Test
    public void view() {
        // 此数据结构不会出现并发修改异常
        ConcurrentHashMap<Integer, Integer> hashMap = new ConcurrentHashMap<>();

        hashMap.putAll(getList().stream().collect(Collectors
                .toMap(Conference::getBeginSign, Conference::getEndSign)));
        //把时间段挨着的进行组合 比如 12-13,13-15 改为 12-15 代表12-15此区间已经被占用
        hashMap.entrySet().forEach(entry -> {
            hashMap.entrySet().forEach(entryInner -> {
                if (entry.getKey() == entryInner.getValue()) {
                    hashMap.put(entryInner.getKey(), entry.getValue());
                    hashMap.remove(entry.getKey());
                }
            });
        });
        // 由于预定的时间段的不冲突和不重合性 排序
        TreeSet<Integer> treeSet = new TreeSet<>();
        hashMap.entrySet().forEach(entry -> {
            treeSet.add(entry.getKey());
            treeSet.add(entry.getValue());
        });
        // 添加 0 分点 和 47 分点 相当于一天的开始和结束
        treeSet.add(BEGIN_SIGN);
        treeSet.add(END_SIGN);

        List<Integer> collect = treeSet.stream().collect(Collectors.toList());
        HashMap<Integer, Integer> resultMap = new HashMap<>();

        // 只要 map 中包含 begin_sign 索引从 1 开始 否则 索引从 0 开始
        boolean flag = false;
        for (int i = 0; i < collect.size() - 1; i += 2) {
            if (!flag && hashMap.containsKey(BEGIN_SIGN)) {
                ++i;
                flag = true;
                resultMap.put(collect.get(i), collect.get(i + 1));
            } else {
                resultMap.put(collect.get(i), collect.get(i + 1));
            }
        }
        System.err.println(resultMap);
    }


    public ArrayList<Conference> getList() {
        ArrayList<Conference> list = new ArrayList<>();
//        list.add(new Conference().setBeginSign(0).setEndSign(7));
        list.add(new Conference().setBeginSign(11).setEndSign(13));
        list.add(new Conference().setBeginSign(14).setEndSign(15));
        list.add(new Conference().setBeginSign(15).setEndSign(17));
        list.add(new Conference().setBeginSign(17).setEndSign(19));
        list.add(new Conference().setBeginSign(20).setEndSign(22));
        list.add(new Conference().setBeginSign(23).setEndSign(25));
        list.add(new Conference().setBeginSign(45).setEndSign(47));
        return list;
    }

    @Data
    @Accessors(chain = true)
    class Conference {
        Integer beginSign;
        Integer endSign;
    }
}
