package leetcode;

/**
 * Created by pingcai on 2017/5/10.
 */
public class _7_Reverse_Integer {
    public static void main(String[] args) {
        System.out.println(new _7_Reverse_Integer().reverse(1));
        System.out.println(new _7_Reverse_Integer().reverse(-1));
        System.out.println(new _7_Reverse_Integer().reverse(Integer.MIN_VALUE));
        System.out.println(new _7_Reverse_Integer().reverse(Integer.MAX_VALUE));
    }

    //方法二：比较当前这个数与上次计算结果是否相同，不相同则是溢出
    public int reverse(int x) {
        int result = 0;
        int next = 0;
        while (x != 0) {
            int end = x % 10;
            x = x / 10;
            next = result * 10 + end;
            if ((next - end) / 10 != result) {
                return 0;
            }
            result = next;
        }
        return result;
    }
 /*
    //方法一：使用long保存x，再判断溢出
    public int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = res * 10 + x % 10;
            x /= 10;
        }
        return res < Integer.MIN_VALUE || res > Integer.MAX_VALUE ? 0 : (int)res;
    }*/
}
