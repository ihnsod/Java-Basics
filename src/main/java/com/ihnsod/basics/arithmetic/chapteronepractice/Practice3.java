package com.ihnsod.basics.arithmetic.chapteronepractice;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Practice3 {
    @Test
    public void method() {
        String s = "ababcdababcd";
        boolean[] b = new boolean[s.length()];
        Map<Character, Set<Integer>> site = new HashMap<>();
        int[] n = {0};
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int finalI = i;
            n[0] = i;
            site.getOrDefault(c, new TreeSet<>()).forEach(ii -> {
                if (ii != finalI - 1 && 2 * finalI - ii <= s.length() && s.substring(ii, finalI)
                        .equals(s.substring(finalI, 2 * finalI - ii))) {
                    IntStream.rangeClosed(ii, finalI - 1).forEach(in -> b[in] = true);
                    n[0] = 2 * finalI - ii - 1;
                }
            });
            for (int j = i; j <= n[0]; j++) {
                char cc = s.charAt(j);
                Set<Integer> set = site.getOrDefault(cc, new TreeSet<>());
                set.add(j);
                site.put(cc, set);
            }
            i = n[0];
        }
        for (int i = 0; i < s.length(); i++) {
            if (!b[i]) {
                System.out.print(s.charAt(i));
            }
        }
    }
}
