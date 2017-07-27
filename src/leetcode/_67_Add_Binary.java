package leetcode;

/**
 * https://leetcode.com/problems/add-binary
 * Created by pingcai on 2017/7/26.
 */
public class _67_Add_Binary {

    public static void main(String[] args) {
        String a = "100";
        String b = "110010";
        System.out.println(new _67_Add_Binary().addBinary(a, b));
    }

    public String addBinary(String a, String b) {
        int aLen = a.length();
        int bLen = b.length();
        int len = aLen > bLen ? aLen : bLen;
        char[] ca = new char[len];
        char[] cb = new char[len];
        for (int i = 0; i < len; i++) {
            ca[i] = '0';
            cb[i] = '0';
        }
        System.arraycopy(a.toCharArray(), 0, ca, len - aLen, aLen);
        System.arraycopy(b.toCharArray(), 0, cb, len - bLen, bLen);

        boolean plus = false; //进位
        int i = len - 1;

        while (i > -1) {
            if ((ca[i] - '0' | cb[i] - '0') == 0) { // 或运算判断结果
                if (plus) {
                    ca[i] = '1';
                    plus = false;
                } else {
                    ca[i] = '0';
                }
            } else {
                if (ca[i] == '1' && cb[i] == '1') {
                    if (plus) {
                        ca[i] = '1';
                    } else {
                        ca[i] = '0';
                    }
                    plus = true;
                } else {
                    if (plus) {
                        ca[i] = '0';
                        plus = true; // 1+0 plus 进位
                    } else {
                        ca[i] = '1';
                    }
                }
            }
            i--;
        }
        return plus?"1"+new String(ca):new String(ca);
    }
}
