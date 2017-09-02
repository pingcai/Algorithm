package algorithm.other;

import java.util.Arrays;

/**
 * 荷兰国旗问题,将三种不同颜色的数组颜色相同的部分放在一起
 * date：2017/9/2　14:22
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class DutchFlag {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 1, 1, 1, 2, 2, 1, 0, 0, 1, 2, 1};
        flag(arr);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 思想来源于快排
     * 三个指针去遍历数组:
     * 目标begin指向0
     * current指向1
     * end指向2
     * 当current为0时,与begin交换,current++,begin++(current++的原因是两个是同一个起点出发)
     * current为1,current++
     * 当current为2,与end交换,end--
     *
     * @param arr
     */
    private static void flag(int[] arr) {
        if (null != arr && arr.length > 1) {
            int begin = 0;
            int current = 0;
            int end = arr.length - 1;
            while (current <= end) {
                if (arr[current] == 0) {
                    swap(arr, begin++, current++);

                } else if (arr[current] == 1) {
                    current++;
                } else if (arr[current] == 2) {
                    swap(arr, current, end--);
                }
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
