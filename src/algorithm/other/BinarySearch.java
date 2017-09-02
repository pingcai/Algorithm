package algorithm.other;

/**
 * 长度为1001的数组存放1~1000,只有一个元素是重复的,找出这个元素
 * date：2017/9/2　14:42
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 5, 6, 7, 9};
        int target = 6;
        System.out.println(binarySearch(arr, target));
    }

    private static int binarySearch(int[] arr, int target) {
        int pos = -1;
        int count = 0;
        if (null != arr && arr.length > 0) {
            int left = 0;
            int right = arr.length - 1;
            int mid = 0;
            while (left <= right) {
                count++;
                mid = left + ((right - left) >> 1);
                if (target == arr[mid]) {
                    pos = mid;
                    break;
                } else if (target > arr[mid]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        System.out.println("比较次数:" + count);
        return pos;
    }

}
