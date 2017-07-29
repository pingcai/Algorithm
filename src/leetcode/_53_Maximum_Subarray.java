package leetcode;

import java.util.Arrays;

/**
 * Created by pingcai on 17-5-2.
 * <p>
 * 动态规划基础
 */
public class _53_Maximum_Subarray {
    public static void main(String[] args) {
        System.out.println(new _53_Maximum_Subarray().maxSubArray(new int[]{1, 2, 1}));
    }

    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length<1){
            return 0;
        }
        int max = nums[0];
        int tmp = 0;
        for(int i=1;i<nums.length;i++){
            tmp = nums[i-1]+nums[i];
            nums[i] = nums[i]>tmp?nums[i]:tmp;
            if(max < nums[i]){
                max = nums[i];
            }
        }
        return max;
    }
}
