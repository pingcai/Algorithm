package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pingcai on 17-4-27.
 */
public class _15_3Sum {

    public static void main(String[] args) {
        int[] a = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(new _15_3Sum().threeSum(a));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return list;
        }
        int i = 0;
        Arrays.sort(nums);
        while (i < nums.length - 2 && nums[i] <= 0) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                i++;
                continue;
            }
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int x = nums[i] + nums[j] + nums[k];
                if (x == 0) {
                    list.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                } else if (x < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            i++;
        }
        return list;
    }
}