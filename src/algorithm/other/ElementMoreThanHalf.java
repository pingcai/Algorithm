package test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 数组中多余一半的元素
 * date：2017/9/4　17:41
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class ElementMoreThanHalf {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 2, 2, 2, 1, 1, 1, 2};
        int[] arr2 = {1, 1, 1, 1, 2, 2, 2};//奇数个
        int[] arr3 = {1, 2, 1, 2, 3, 1, 2, 2};//偶数个
        System.out.println(getElement3(arr3));
    }

    /**
     * 计数器,碰到相同数则++,否则--
     *
     * @param arr
     * @return
     */
    private static int getElement3(int[] arr) {
        int n = 1;
        int cur = arr[0];
        for (int j = 1;j<arr.length;j++) {
            if (arr[j] == cur) {
                n++;
            } else {
                if(n == 1){
                    cur = arr[j];
                }else{
                    n--;
                }
            }
        }
        return cur;
    }

    private static int getElement2(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        for (int i : arr) {
            if (stack.empty()) {
                stack.push(i);
            } else if (stack.peek() != i) {
                stack.pop();
            } else {
                stack.push(i);
            }
        }
        return stack.pop();
    }


    /**
     * hash
     *
     * @param arr
     * @return
     */
    private static int getElement1(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.get(arr[i]) == null ? 1 : map.get(arr[i]) + 1);
        }
        int max = Integer.MIN_VALUE;
        int max_index = 0;
        for (Map.Entry<Integer, Integer> en : map.entrySet()) {
            if (en.getValue() > max) {
                max = en.getValue();
                max_index = en.getKey();
            }
        }
        return max_index;
    }

    /**
     * 排序后取中间值
     *
     * @param arr
     * @return
     */
    private static int getElement0(int[] arr) {
        Arrays.sort(arr);
        return arr[arr.length >> 1];
    }
}
