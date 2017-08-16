package algorithm.sort;

import java.util.Arrays;

/**
 * 核心思想:左大右小+递归
 * date：2017/8/17
 * author：黄平财
 */
public class QuickSort {

    public static void main(String[] args){
        int[] arr = new int[]{1,123,2,36,35345,234,123123,-1231,4368,-234,4577755,-23423,9};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] arr) {
        quickSort(arr,0,arr.length-1);
    }

    /**
     * 元素站队,小的在左边,大的在右边
     * @param l 左边的元素
     * @param r 右边的元素
     */
    public static void quickSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = p(arr, l, r);
            quickSort(arr, l, m - 1);
            quickSort(arr, m + 1, r);
        }
    }

    private static int p(int[] arr, int l, int r) {
        int tmp = arr[l]; //默认以第一个为基准，如需要别的数，直接跟0下标交换
        while (l < r) {
            // 1.从右往左找到小于 tmp的数，并交换
            while (l < r && arr[r] >= tmp) r--;
            if (l < r) arr[l] = arr[r];
            // 2.从左往右找到大于 tmp的数，并交换
            while (l < r && arr[l] <= tmp) l++;
            if (l < r) arr[r] = arr[l];
        }
        arr[l] = tmp;
        return l;
    }
}
