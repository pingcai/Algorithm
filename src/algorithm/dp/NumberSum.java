package algorithm.dp;

/**
 * 题目描述:https://www.nowcoder.com/questionTerminal/7f24eb7266ce4b0792ce8721d6259800
 * 求aim在数组中的组合数,每个数只能使用一次
 * date：2017/8/28　13:06
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class NumberSum {
    public static void main(String[] args) {
        int[] arr = {5, 5,10, 2, 3};
        int aim = 15;
        System.out.println(bf(arr, aim));
    }

    private static int bf(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        boolean[] book = new boolean[arr.length];
        return process(arr, book, 0, aim);
    }


    private static int process(int[] arr, boolean[] book, int i, int aim) {
        int len = arr.length;
        int res = 0;
        if (aim == 0) {
            res++;
        } else if (aim < 0) {
            return 0;
        } else {
            for (int j = i + 1; j < len; j++) {
                res += process(arr, book, j, aim - arr[j]);
            }
        }
        return res;
    }
}
