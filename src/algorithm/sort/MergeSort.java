package algorithm.sort;

import java.util.Arrays;

/**
 * date：2017/9/2　22:58
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class MergeSort {
    public static void main(String[] args){
        int[] arr = {234,2,234234234,-3,23,-9,777,39,12,-55};
        System.out.println(Arrays.toString(arr));
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void sort(int[] arr) {
        if(arr != null && arr.length > 1){
            mergeSort(arr,0,arr.length-1);
        }
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if(l < r){
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int[] tmpArr = new int[arr.length];//临时数组
        int i = l;//临时数组排序过程中的索引
        int j = l;//原数组前半部分的索引
        int k = m + 1;//原数组后半部分的索引
        while (j <= m && k <= r) {
            //从两个数组中取出最小的放入中间数组
            if (arr[j] <= arr[k]) {
                tmpArr[i++] = arr[j++];
            } else {
                tmpArr[i++] = arr[k++];
            }
        }
        //剩余部分依次放入中间数组
        while (j <= m) tmpArr[i++] = arr[j++];
        while (k <= r) tmpArr[i++] = arr[k++];
        //将中间数组中的内容复制回原数组
        while (l <= r) arr[l] = tmpArr[l++];
    }
}
