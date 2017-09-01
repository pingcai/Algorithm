package leetcode;

/**
 * 求最大子数组乘积
 * https://leetcode.com/problems/maximum-product-subarray/description/
 * date：2017/9/2　1:57
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class _152_Maximum_Product_Subarray {
    public static void main(String[] args) {
        int[] arr = {6,3,-10,0,2};
        System.out.println(new _152_Maximum_Product_Subarray().maxProduct(arr));
    }

    /**
     * 记录最大最小数
     */
    public int maxProduct(int[] nums) {
        int last_max = nums[0];
        int last_min = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = nums[i];
            int cur_max = Math.max(cur, Math.max(last_max * cur, last_min * cur));
            int cur_min = Math.min(cur, Math.min(last_max * cur,last_min * cur));
            res = Math.max(res,cur_max);
            last_max = cur_max;
            last_min = cur_min;
        }
        return res;
    }
}
