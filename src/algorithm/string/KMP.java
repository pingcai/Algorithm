package algorithm.string;

import java.util.Arrays;

/**
 * date：2017/8/14
 * author：黄平财
 */
public class KMP {
    public static void main(String[] args) {
        String text = "abcdefabcdagdea";
        String pattern = "abbcdabbc";
        System.out.println(kmp(text, pattern));
    }

    /**
     * 思想:
     * 1.本质是暴力的优化版,遍历text的i一直不变,变的只是pattern的j;
     * 2.next数组就是当text[i]!=pattern[j]时,j从哪里重新开始(暴力是每次都从0开始)
     * 时间复杂度:O(m+n)
     * @param text
     * @param pattern
     * @return
     */
    private static int kmp(String text, String pattern) {
        if ((text == null && pattern != null) || (text == null && pattern != null) || text.length() < pattern.length()) {
            return -1;
        }
        // 1.获取next数组
        int[] next = getNext(pattern);
        System.out.println(Arrays.toString(next));
        //2.kmp跳跃遍历
        int M = text.length();
        int N = pattern.length();
        int i = 0;
        int j = 0;
        while (i < M && j < N) {
            if (j == -1 || text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
            if (N == j) {
                return i - j;
            }
        }
        return -1;
    }

    /**
     * 获取next数组,即当text[i]和pattern[j]匹配失败时,j应该从哪个位置开始
     * 这是KMP的精髓,暴力一直让j从0重新开始,而KMP选择最佳的位置重新开始
     *
     * 1.获取最长的前缀后缀匹配longest[]
     * 2.next[]等于longest[]的第一位为-1,然后整体元素向右移一位
     *
     * @param pattern
     * @return
     */
    private static int[] getNext(String pattern) {
        int len = pattern.length();
        int[] next = new int[len];
        int k = -1;
        next[0] = k;
        int j = 0;
        while (j < len - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                k++;
                j++;
                if(next[j] != next[k]){
                    next[j] = k;
                }else{
                    // 优化,当j将要回退到k时,next[j]应不等于next[k],因为next[j]已经不匹配了,再回去next[k]也一样是不匹配的
                    // 所以再继续回退到next[k]
                    next[j] = next[k];
                }
            } else {
                k = next[k];
            }
        }
        return next;
    }


    /**
     * 暴力解法
     *
     * @param text
     * @param pattern
     * @return
     */
    private static int search0(String text, String pattern) {

        int M = pattern.length();
        int N = text.length();
        //i指向开头
        for (int i = 0; i <= N - M; i++) {
            int j = 0;
            int k = i;
            for (; j < M; j++) {
                if (text.charAt(k++) != pattern.charAt(j)) {
                    break;
                }
            }
            if (j == M) {
                return i;
            }
        }
        return -1;
    }

    //另一个暴力破解
    private static int search1(String text, String pattern) {
        int M = pattern.length();
        int N = text.length();
        //这里的i指向结尾
        int i = 0, j = 0;
        for (; i < N && j < M; i++) {
            if (text.charAt(i) == pattern.charAt(j)) {
                j++;
            } else {
                i -= j; // 回退
                j = 0;
            }
        }
        if (j == M) {
            return i - M;
        }
        return -1;
    }

}
