package algorithm.dp;

/**
 * date：2017/8/27　16:24
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(jump2(4));
    }

    /**
     * 青蛙跳台阶1，一次只能跳1级或2级（斐波那契数列）
     *
     * @param n
     * @return
     */
    private static int jump1(int n) {
        if (n <= 0) {
            return 0;
        } else if (1 == n) {
            return 1;
        } else if (2 == n) {
            return 2;
        }
        return jump1(n - 1) + jump1(n - 2);
    }


    /**
     * 青蛙跳台阶1，一次可以调1-n级
     * 递推表达式：
     * 0 表示直接跳n级
     * 1 一次跳一级
     * 2 一次跳两级
     * n 第一次跳1级还剩F(n-1)中跳法，第一次跳2级还剩F(n-2)中跳法，第一次跳3级还有F(n-3)中跳法
     * F(n)=F(n-1)+F(n-2)+F(n-3)+F(n-4)...+F(0)，而
     * F(n-1)=F(n-2)+F(n-3)+F(n-4)...+F(0)
     * 两式想减，得 F(n) = 2F(n-1)
     * @param n
     * @return
     */
    private static int jump2(int n) {
        if (n <= 0) {
            return 1;
        } else if (1 == n) {
            return 1;
        } else if (2 == n) {
            return 2;
        }
        return 2*jump2(n - 1);
    }


}
