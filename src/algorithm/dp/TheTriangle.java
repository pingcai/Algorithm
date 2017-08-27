package algorithm.dp;

import java.util.Scanner;

/**
 * http://poj.org/problem?id=1163
 * 7
 * 3   8
 * 8   1   0
 * 2   7   4   4
 * 4   5   2   6   5
 * <p>
 *     自底向上累加
 * <p>
 * date：2017/8/27　21:44
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class TheTriangle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        int[][] arr = new int[len][len];
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                arr[i][j] = sc.nextInt();
                if (i == j) {
                    break;
                }
            }
        }
        for (int i = len - 2; i > -1; i--) {
            for (int j = len - 2; j > -1; j--) {
                int l = arr[i + 1][j];
                int r = arr[i + 1][j + 1];
                arr[i][j] += Math.max(l, r);
            }
        }
        System.out.println(arr[0][0]);
    }
}
