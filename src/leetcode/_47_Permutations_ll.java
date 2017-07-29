package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pingcai on 2017/5/23.
 * <p>
 * https://leetcode.com/problems/permutations/#/description
 * <p>
 * 求一个数组的所有元素的组合(包含重复元素)
 * <p>
 * 使用一个book数组记录使用过的数,在递归
 */
public class _47_Permutations_ll {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2};
        List list = new _47_Permutations_ll().permuteUnique(arr);
        System.out.println(list);
        System.out.println(list.size());
    }

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int size = nums.length;
        Arrays.sort(nums);
        boolean[] book = new boolean[size];
        backtrace(0, nums, book, new ArrayList<Integer>());
        return result;
    }

    private void backtrace(int size, int[] nums, boolean[] book, ArrayList<Integer> list) {
        if (size == nums.length) {
            if (!result.contains(list)) {
                result.add(new ArrayList<>(list));
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (book[i] || (i > 0 && nums[i] == nums[i - 1] && book[i - 1])) {
                    continue;
                }
                list.add(nums[i]);
                book[i] = true;
                backtrace(size + 1, nums, book, list);
                list.remove(list.size() - 1);
                book[i] = false;
            }
        }
    }
}
