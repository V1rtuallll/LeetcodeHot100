package lc76_最小覆盖子串;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 我的数组写法
 */
class Solution {
    public String minWindow(String s, String t) {
        int[] need = new int[128];//注意 A a不是连续映射 开128能直接赋值
        int[] cur = new int[128];
        int size = 0;
        int left = 0;
        char[] str = s.toCharArray();
        int valid = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;
        for (char c : t.toCharArray()) {
            if (need[c] == 0) size++;
            need[c]++;
        }
        for (int right = 0; right < s.length(); right++) {
            int r_index = str[right];
            cur[r_index]++;
            if (cur[r_index] == need[r_index]) {
                valid++;
            }
            while (valid == size) { //注意要一直缩到不满足为止
                int l_index = str[left];
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }
                if (cur[l_index] == need[l_index]) {
                    valid--;
                }
                cur[l_index]--;
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE
                ? ""
                : s.substring(start, start + minLen);
    }
}

//以下是老的写法
//public class Solution {
//    public String minWindow(String s, String t) {
//        Map<Character, Integer> need = new HashMap<>();
//        Map<Character, Integer> cur = new HashMap<>();
//        for (char c : t.toCharArray()) {
//            need.put(c, need.containsKey(c) ? need.get(c) + 1 : 1);
//        }
//        int left = 0;
//        int right = 0;
//        int valid = 0;
//        String res = "";
//        while (right < s.length()) {
//            char right_char = s.charAt(right);
//            right++;
//
//            // 扩张
//            if (need.containsKey(right_char)) {
//                cur.put(right_char, cur.getOrDefault(right_char, 0) + 1);
//                if (cur.get(right_char).equals(need.get(right_char))) {
//                    valid++;
//                }
//            }
//
//            // 收缩
//            while (valid == need.size()) {
//                char left_char = s.charAt(left);
//
//                // 更新答案
//                if (res.equals("") || right - left < res.length()) {
//                    res = s.substring(left, right);
//                }
//
//                left++;
//
//                if (need.containsKey(left_char)) {
//                    if (cur.get(left_char).equals(need.get(left_char))) {
//                        valid--;
//                    }
//                    cur.put(left_char, cur.get(left_char) - 1);
//                }
//            }
//        }
//        return res;
//    }
//}
