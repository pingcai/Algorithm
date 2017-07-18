package leetcode;

/**
 * Created by pingcai on 2017/7/18.
 */
public class _459_Repeated_Substring_Pattern {
    public static void main(String[] args) {
        String str = "babbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabbbabbbbaabb";
        System.out.println(new _459_Repeated_Substring_Pattern().repeatedSubstringPattern(str));
    }

    /**
     * 简单的正则匹配
     * 大牛的kmp解法:https://discuss.leetcode.com/topic/67590/java-o-n
     *
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern(String s) {
        String PATTERN = "(%s){2,}";
        int len = s.length();
        char c = s.charAt(len - 1);
        int end = s.indexOf(c);
        while (end >= 0 && end < s.length() / 2) {
            if (len % (end + 1) == 0 && s.matches(String.format(PATTERN, s.substring(0, end + 1)))) {
                return true;
            }
            end = s.indexOf(c, end + 1);
        }
        return false;
    }
}
