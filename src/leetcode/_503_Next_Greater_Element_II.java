package leetcode;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by pingcai on 17-5-2.
 *
 * 找出数组中每一元素右边大于它的数，没有置为-1，循环结构
 * 是496题的变种
 */
public class _503_Next_Greater_Element_II {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _503_Next_Greater_Element_II().nextGreaterElements(new int[]{1,2,1})));
    }

    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        Stack<Integer> stack = new Stack<>();
        int len = nums.length;
        int[] res = new int[len];
        int n = 0;
        for (int i = 0; i < len * 2; i++) {
            n = nums[i % len];
            while (!stack.empty() && nums[stack.peek()] < n) {
                res[stack.pop()] = n;
            }
            if (i < len) {
                stack.push(i);
                res[i] = -1;
            }
        }
        return res;
    }




/*
    蛮力法
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int len = nums.length;
        int[] tmp = new int[2 * len];
        System.arraycopy(nums, 0, tmp, 0, len);
        for (int i = len; i < tmp.length; i++) {
            tmp[i] = nums[i - len];
        }
        for (int j = 0; j < len; j++) {
            nums[j] = find(tmp, j);
        }
        return nums;
    }
    public int find(int[] arr, int i) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] < arr[j]) {
                return arr[j];
            }
        }
        return -1;
    }*/
}
