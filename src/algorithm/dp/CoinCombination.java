package algorithm.dp;

/**
 * 硬币组合，给定一个数组A，一个目标金额aim，求aim的所有组合方法
 * <p>
 * date：2017/8/27　14:57
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class CoinCombination {
    public static void main(String[] args) {
        int[] A = new int[]{5, 10, 25, 1};
        int aim = 0;
        System.out.println(bf(A, aim));
    }

    private static int dp(int[] a, int aim) {
        return 0;
    }

    /**
     * 暴力解，
     * 0个5
     * 1个5
     * 2个5
     * ...
     * 大量重复计算
     */
    private static int bf(int[] A, int aim) {
        if (null == A || A.length == 0 || aim <= 0) {
            return 0;
        }
        return process0(A, 0, aim);
    }

    private static int process0(int[] A, int index, int aim) {
        int res = 0;
        if (index == A.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int j = 0; j * A[index] <= aim; j++) {
                res += process0(A, index + 1, aim - j * A[index]);
            }
        }
        return res;
    }

    /**
     * 记忆化
     */
    private static int mm(int[] A, int aim) {
        if (null == A || A.length == 0 || aim <= 0) {
            return 0;
        }
        //map[i][j]表示i位置到结尾aim为j有多少个
        int[][] map = new int[A.length + 1][aim + 1];
        return process1(A, 0, aim, map);
    }

    private static int process1(int[] A, int index, int aim, int[][] map) {
        int res = 0;
        if (index == A.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; i * A[index] <= aim; i++) {
                mapValue = map[index + 1][aim - i * A[index]];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process1(A, index + 1, aim - i * A[index], map);
                }
            }
            map[index][aim] = res == 0 ? -1 : res;
        }
        return res;
    }
}
