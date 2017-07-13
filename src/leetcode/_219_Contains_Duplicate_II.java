package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pingcai on 17-7-13.
 *
 * 求数组里是否包含距离在k之内的两个相同数
 *
 * 如： arr = 1 9 8 7 8 3  k = 3 返回 true
 *
 *      arr = 1 9 8 7 8 3  k = 1 返回 false
 */
public class _219_Contains_Duplicate_II {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap();
        int q = 0;
        for(int i =0;i<nums.length;i++){
            q = nums[i];
            if(map.get(q) != null && i - map.get(q) <= k){
                return true;
            }
            map.put(q,i);
        }
        return false;
    }
}
