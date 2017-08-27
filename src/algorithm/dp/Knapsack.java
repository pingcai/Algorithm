package algorithm.dp;

/**
 * date：2017/8/27　20:30
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] w = new int[]{5, 4, 7, 2, 6};
        int[] p = new int[]{12, 3, 10, 3, 6};
        int capital = 10;
        System.out.println(_01Knapsack(w, p, capital));
    }

    /**
     * 01 背包问题,先赋初值，再进行迭代计算
     * 递推方程：dp[i][j] = max{dp[i-1][j],dp[i-1][j-w[i]]+p[i]}
     * @param w       重量
     * @param p       价格
     * @param capital 总容量
     * @return
     */
    private static int _01Knapsack(int[] w, int[] p, int capital) {
        int M = w.length;
        // 表示前M个物品放入容量为N的背包获得的最大价值
        int[][] dp = new int[M][capital];

        boolean bool = false;
        for (int i = 0; i < capital; i++) {
            if (bool || i > w[0]) {
                dp[0][i] = p[0];
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 0; j < capital; j++) {
                int x = dp[i - 1][j];
                int wIndex = j - w[i]; // 防止越界
                int y = -1;
                if (wIndex > -1) {
                    y = dp[i - 1][wIndex] + p[i];
                }
                dp[i][j] = Math.max(x, y);
            }
        }

        return dp[M - 1][capital - 1];
    }


}
