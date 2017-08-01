package leetcode;

/**
 * Given a string and an integer k,
 * you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them.
 * If there are less than 2k but greater than or equal to k characters,
 * then reverse the first k characters and left the other as original.
 * <p>
 * 旋转前２ｋ个字符的ｋ个字符
 * Example:
 * 　Input: s = "abcdefg", k = 2
 * 　Output: "bacdfeg"
 * <p>
 * date：2017/8/1
 * author：黄平财
 */
public class _541_Reverse_String_II {
    public static void main(String[] args) {
        String str = "abcdefgh";
        int i = 3;
        System.out.println(new _541_Reverse_String_II().reverseStr(str, i));
    }

    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
