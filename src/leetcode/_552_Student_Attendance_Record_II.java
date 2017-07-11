package leetcode;

/**
 * 动态规划，暂时没想出来，先参考大牛的题解：http://bookshadow.com/weblog/2017/04/16/leetcode-student-attendance-record-ii/
 */
public class _552_Student_Attendance_Record_II{

    private final int MOD = 1000000007;
    public long sum(int[] nums) {
        long ans = 0;
        for (int n : nums) ans += n;
        return ans % MOD;
    }
    
    public int checkRecord(int n) {
        int dp[][] = {{1, 1, 0}, {1, 0, 0}};
        for (int i = 2; i <= n; i++) {
            int ndp[][] = {{0, 0, 0}, {0, 0, 0}};
            ndp[0][0] = (int)sum(dp[0]);
            ndp[0][1] = dp[0][0];
            ndp[0][2] = dp[0][1];
            ndp[1][0] = (int)((sum(dp[0]) + sum(dp[1])) % MOD);
            ndp[1][1] = dp[1][0];
            ndp[1][2] = dp[1][1];
            dp = ndp;
        }
        return (int)((sum(dp[0]) + sum(dp[1])) % MOD);
    }
}
