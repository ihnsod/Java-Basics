package com.ihnsod.basics.concurrent.base;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author: Ihnsod
 * @create: 2019/05/06 22:00
 **/
public class ForkJoinExample extends RecursiveTask<Integer> {

    private static final int SOLT = 2;
    private int start;
    private int end;

    public ForkJoinExample(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        boolean canCompute = (end - start) <= SOLT;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            ForkJoinExample left = new ForkJoinExample(start, middle);
            ForkJoinExample right = new ForkJoinExample(middle + 1, end);

            // 子任务执行
            left.fork();
            right.fork();

            // 获取子任务的执行结果
            Integer leftJoin = left.join();
            Integer rightJoin = right.join();

            sum = leftJoin + rightJoin;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        ForkJoinExample task = new ForkJoinExample(1, 4);
        // 可以通过此方法来获取任务是否被中断或者是被取消
        if (task.isCompletedAbnormally()){
            task.getException();
        }
        // 执行一个任务
        Future<Integer> result = forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
        } catch (ExecutionException e) {
        }
    }
}
