package leetcode;

/**
 * https://leetcode.com/problems/house-robber/description/
 * 解：https://discuss.leetcode.com/topic/12024/java-dp-solution-o-n-runtime-and-o-1-space-with-inline-comment
 * date：2017/8/27　22:34
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class _198_House_Robber {

    public static void main(String[] args){
        int[] arr = new int[]{2,3,11,9,2};
        System.out.println(new _198_House_Robber().rob(arr));
    }

    public int rob(int[] nums) {
        int rob = 0;
        int noRob = 0;//上一次不偷
        for (int i = 0; i < nums.length; i++) {
            int curRob = noRob + nums[i]; // 偷当前
            noRob = Math.max(noRob,rob);
            rob = curRob;
        }
        return Math.max(rob,noRob);
    }
}
