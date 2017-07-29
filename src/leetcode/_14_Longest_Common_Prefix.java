package leetcode;

import java.util.Arrays;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * <p>
 * date：2017/7/30
 * author：黄平财
 */
public class _14_Longest_Common_Prefix {

    public static void main(String[] args) {
        System.out.println(new _14_Longest_Common_Prefix().longestCommonPrefix(new String[]{"aaaaaaaaa", "a"}));
    }

    /**
     * 排序后只比较最长和最短的两个字符串
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        char[] a = strs[0].toCharArray();
        char[] b = strs[strs.length - 1].toCharArray();
        int i = 0;
        while (i < a.length && i<b.length && a[i] == b[i]) {
            i++;
        }
        return new String(a, 0, i);
    }

    /**
     * 不断截取比较
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            while (strs[i].indexOf(pre) != 0)
                pre = pre.substring(0, pre.length() - 1);
            i++;
        }
        return pre;
    }


}
