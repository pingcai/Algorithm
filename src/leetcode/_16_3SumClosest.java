package leetcode;

import java.util.Arrays;

/**
 * Created by pingcai on 17-4-29.
 * <p>
 * Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target.
 * Return the sum of the three integers. You may assume that each input would have exactly one solution.
 * For example, given array S = {-1 2 1 -4}, and target = 1.
 * The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 * <p>
 * 思路：
 * 先排序再枚举，找出和绝对值最接近target的三个数
 */

public class _16_3SumClosest {

    public int threeSumClosest(int[] nums, int target) {

        int len = nums.length;
        int res = nums[0] + nums[1] + nums[len - 1];
        int j = 0;
        int k = 0;
        int x = 0;
        Arrays.sort(nums);
        for (int i = 0; i < len - 2; i++) {
            j = i + 1;
            k = len - 1;
            while (j < k) {
                x = nums[i] + nums[j] + nums[k];
                if (x > target) {
                    k--;
                } else if (x < target) {
                    j++;
                } else {
                    return x;
                }
                if (Math.abs(target - x) < Math.abs(target - res)) {
                    res = x;
                }
            }

        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[]{13, 2, 0, -14, -20, 19, 8, -5, -13, -3, 20, 15, 20, 5, 13, 14, -17, -7, 12, -6, 0, 20, -19, -1, -15, -2, 8, -2, -9, 13, 0, -3, -18, -9};
        //int[] a = new int[]{0,2,1,-3};
        System.out.println(new _16_3SumClosest().threeSumClosest(a, -59));
    }
}