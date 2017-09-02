package algorithm.other;

import java.util.Arrays;

/**
 * 经典题目,堆排序的应用,时间复杂度:O(nlog2k)
 * date：2017/9/2　16:48
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class TopK {
    public static void main(String[] args) {
        int[] arr = {222222, 3, 86, 555, 34, 23, -99, 8, -999, 234, 88, -9, 888};
        int k = 3;

        System.out.println(Arrays.toString(topK(arr, k)));

    }

    /**
     * 维持一个小顶堆
     *
     * @param arr
     * @param k
     * @return
     */
    private static int[] topK(int[] arr, int k) {
        int[] res = null;
        if (arr != null && arr.length >= k) {
            res = new int[k];
            System.arraycopy(arr, 0, res, 0, k);
            buildHeap(res);
            for (int i = k; i < arr.length; i++) {
                if(arr[i]>res[0]){
                    res[0] = arr[i];
                    heapify(res,0,res.length);
                }
            }
        }
        return res;
    }

    /**
     * 构建堆
     *
     * @param arr
     */
    private static void buildHeap(int[] arr) {
        int index = (arr.length - 1) >> 1;
        for (int i = index; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
    }

    /**
     * 堆化,即重新调整的结构使其满足堆排序的特点
     *
     * @param arr
     * @param i
     * @param length
     */
    private static void heapify(int[] arr, int i, int length) {
        int left = 2 * i;
        int right = left + 1;
        int minPos = i;
        int min = arr[i];
        if (left < length && arr[left] < min) {
            minPos = left;
            min = arr[left];
        }
        if (right < length && arr[right] < min) {
            min = arr[right];
            minPos = right;
        }
        if (min < arr[i]) {
            swap(arr, i, minPos);
            heapify(arr, minPos, length);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
