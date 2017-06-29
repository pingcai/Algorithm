import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by pingcai on 17-5-2.
 *
 * 找出数组中每一元素右边大于它的数，没有置为-1
 *
 */
public class _496_Next_Greater_Element_I {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new _496_Next_Greater_Element_I().nextGreaterElement(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2})));
    }


    // 堆栈相结合
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null || findNums.length <= 0) {
            return findNums;
        }

        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack();

        for (int i : nums) {
            while (!stack.empty() && stack.peek() < i) {
                map.put(stack.pop(), i);
            }
            stack.push(i);
        }
        for (int i = 0; i < findNums.length; i++) {
            findNums[i] = map.getOrDefault(findNums[i], -1);
        }
        return findNums;
    }


    /*
    //提前用 map保存nums的下标，则查找时间复杂度为O（1）
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if (findNums == null || findNums.length <= 0) {
            return findNums;
        }
        Map<Integer, Integer> map = new HashMap<>();

        int q = 0;
        for (int i : nums) {
            map.put(i, q++);
        }
        for (int i = 0; i < findNums.length; i++) {
            int start = map.get(findNums[i]);
            int b = findNums[i];
            findNums[i] = -1;
            for (int j = start + 1; j < nums.length; j++) {
                if (nums[j] > b) {
                    findNums[i] = nums[j];
                    break;
                }
            }
        }
        return findNums;
    }

    // 蛮力法
    public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if(findNums == null || findNums.length <= 0){
            return findNums;
        }
        int i = 0;
        for(int x : findNums){
            int b = find(nums,x);
            findNums[i++] = b;
        }
        return findNums;
    }


    public int find(int[] nums,int i){
        int max = -1;
        for(int x = nums.length-1;x>0;x--){
            if(nums[x]==i){
                return max;
            }
            if(nums[x]>i){
                max = nums[x];
            }
        }
        return max;
    }*/
}
