package leetcode;

import java.util.Arrays;

/**
 * Created by pingcai on 17-7-7.
 */
public class _26Remove_Duplicates_from_Sorted_Array {
    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4, 5, 5, 5, 5, 5};
        //int[] arr = {1,2};
        System.out.println(new _26Remove_Duplicates_from_Sorted_Array().removeDuplicates(arr));
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 本题的要点是：数组已经排好序，需要做的是用一个　p 记录相同元素间的间隔
     * 当碰到下一个不相同的元素nums[i]时，将其移动到nums[i-p]
     */

    public int removeDuplicates(int[] nums) {
        int len = 0;
        int p = 0;
        int i = 0;
        if (nums != null && nums.length > 1) {
            while (i < nums.length) {
                if (i > 0 && nums[i] == nums[i - 1]) {
                    p++;
                } else {
                    len++;
                    nums[i - p] = nums[i];
                }
                i++;
            }
        } else if (nums.length == 1) {
            len = 1;
        }
        return len;
    }
}
