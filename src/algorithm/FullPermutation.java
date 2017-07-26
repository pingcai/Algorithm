package algorithm;

/**
 * 全排列：给定一个字符串数组，求出所有的组合方式；
 * 如：a b c 可组成：
 * abc acb
 * bac bca
 * cab cba
 * <p>
 * Created by pingcai on 2017/7/26.
 */
public class FullPermutation {
    public static void main(String[] args) {
        char[] chas = "abcd".toCharArray();
        fullPermutation(chas, 0, chas.length - 1);
    }

    /**
     * 重点是交换的过程，和暴力找最短路径类似
     * @param arr
     * @param from
     * @param to
     */
    public static void fullPermutation(char[] arr, int from, int to) {
        if (from == to) {
            System.out.println(new String(arr, 0, to+1));
        } else {
            for (int i = from; i <= to; i++) {
                swap(arr, i, from);
                fullPermutation(arr,from+1,to);
                swap(arr, i, from);
            }
        }

    }

    private static void swap(char[] arr, int from, int to) {
        char t = arr[from];
        arr[from] = arr[to];
        arr[to] = t;
    }

}
