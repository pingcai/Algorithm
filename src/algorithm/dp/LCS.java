package algorithm.dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 * date：2017/8/27　19:57
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class LCS {
    public static void main(String[] args) {

        String a = "a4j3kdhc";
        String b = "43kdc";

        System.out.println(lcs(a, b));
    }

    private static int lcs(String a, String b) {
        int M = a.length();
        int N = b.length();
        int[][] dp = new int[M][N];
        boolean bool = false;
        for (int i = 0; i < M; i++) {
            if (bool || a.charAt(i) == b.charAt(0)) {
                dp[i][0] = 1;
                bool = true;
            }
        }
        bool = false;
        for (int i = 0; i < N; i++) {
            if (bool || b.charAt(i) == a.charAt(0)) {
                dp[0][i] = 1;
            }
        }

        for (int i = 1; i < M; i++) {
            for (int j = 1; j < N; j++) {
                // 只可能有三种情况
                int x = dp[i - 1][j];
                int y = dp[i][j - 1];
                int z = dp[i - 1][j - 1] + 1;
                dp[i][j] = max(x, y, z);
            }
        }
        return dp[M - 1][N - 1];
    }

    private static int max(int x, int y, int z) {
        int res = x > y ? (x > z ? x : z) : (y > z ? y : z);;
        return res;
    }

}
