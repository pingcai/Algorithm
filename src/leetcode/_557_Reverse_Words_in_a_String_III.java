package leetcode;

public class _557_Reverse_Words_in_a_String_III {
    public static void main(String[] ar) {
        String str = " contest";
        System.out.println(new _557_Reverse_Words_in_a_String_III().reverseWords(str));
    }

    //"s'teL ekat edoCteeL tsetnoc"
    public String reverseWords(String s) {
        char[] arr = s.toCharArray();
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] == 32) {//空格的ＡＳＣＩＩ码为　３２
                reverse(arr, p, i - 1);
                p = i + 1;
            } else if (i == s.length() - 1) {
                reverse(arr, p, i);
            }
        }
        return new String(arr);
    }

    private void reverse(char[] ca, int i, int j) {
        for (; i < j; i++, j--) {
            char tmp = ca[i];
            ca[i] = ca[j];
            ca[j] = tmp;
        }
    }
}
