package leetcode;

/**
 * https://leetcode.com/problems/climbing-stairs/description/
 * 青蛙跳台阶
 * date：2017/8/31　0:05
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class _70_Climbing_Stairs {
    public static void main(String[] args) {
        int stairs = 5;
        System.out.println(new _70_Climbing_Stairs().climbStairs(stairs));
    }

    public int climbStairs(int n) {
        return r(n);
    }

    /**
     * 递归:  1 2 3 5 8 13
     *
     * @param n
     * @return
     */
    private int r(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        int i = 1;
        int j = 2;
        int k = 3;
        int c = 3;
        while (c++ < n) {
            i = j;
            j = k;
            k = i + j;
        }
        return k;
    }
}
