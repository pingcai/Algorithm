package algorithm.dp;

import java.util.Scanner;

/**
 * 页码统计：https://www.nowcoder.com/questionTerminal/3a003cb6a3174ef9835fa603e01d8b52
 * date：2017/8/27　22:02
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class PageCount {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[10];
        count(arr,n);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "\t");
        }
    }

    private static void count(int[] arr, int n) {

    }
}
