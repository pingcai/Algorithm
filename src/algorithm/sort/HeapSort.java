package algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序：
 * 左节点=2*父节点 + 1
 * 右节点=2*父节点 + 2
 * date：2017/8/12
 * author：黄平财
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 234, 4, 7, 2, 234, -35, 999, 7, 8888, 23423, 9, 12, 78};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 1.构建堆
        int maxIndex = arr.length - 1;
        int maxParentIndex = (maxIndex - 1) >> 1; // 从最后一个父节点开始调整
        for (int i = maxParentIndex; i >= 0; i--) {
            build(arr, i, maxIndex);
        }
        // 2.排序
        for (int i = maxIndex; i > 0; i--) {
            swap(arr, 0, i);
            build(arr, 0, i - 1);
        }
    }

    private static void build(int[] arr, int parent, int maxIndex) {
        int left = (parent << 1) + 1;
        int right = left + 1;
        int max = left;
        if (left > maxIndex) {
            return;
        }
        if (right <= maxIndex && arr[left] < arr[right]) {
            max = right;
        }
        if (arr[max] > arr[parent]) {
            swap(arr, parent, max);
            build(arr, max, maxIndex);  //依次递归往下调整
        }
    }

    private static void swap(int[] arr, int a, int b) {
        int t = arr[a];
        arr[a] = arr[b];
        arr[b] = t;
    }


}
