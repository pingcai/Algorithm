package algorithm.dp;

/**
 * 求二维数组的最大2*2子数组
 * 思路:压缩列
 * date：2017/9/2　2:15
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class MaxSubMatrix {
    public static void main(String[] args) {
        int[][] arr = {
                {2, 5, -1},
                {-5, 1, 3},
                {1, 2, 9}
        };
        System.out.println(maxSubMatrix(arr));
    }

    private static int maxSubMatrix(int[][] arr) {
        int r = arr.length;//行
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < r - 1; i++) {
            int[] sub = comp(arr, i, i+1);
            max = Math.max(max, maxSubArray(sub));
        }
        return max;
    }

    private static int maxSubArray(int[] sub) {
        int maxSum = sub[0];
        int curSum = sub[0];
        for (int i = 1; i < sub.length; i++) {
            if (curSum >= 0) {
                curSum += sub[i];
            } else {
                curSum = sub[i];
            }
            if (maxSum < curSum) {
                maxSum = curSum;
            }
        }
        return maxSum;
    }

    private static int[] comp(int[][] arr, int i, int j) {
        int[] sub = new int[arr[0].length];
        for (int k = 0; k < sub.length; k++) {
            sub[k] = colsPlus(arr, k, i, j);
        }
        return sub;
    }

    /**
     * @param arr
     * @param k   当前列
     * @param i   最小行
     * @param j   最小列
     * @return
     */
    private static int colsPlus(int[][] arr, int k, int i, int j) {
        int res = 0;
        for (int l = i; l <= j; l++) {
            res += arr[l][k];
        }
        return res;
    }
}
