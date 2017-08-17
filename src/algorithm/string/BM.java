package algorithm.string;

/**
 * 思路来源:http://blog.csdn.net/v_july_v/article/details/7041827,讲得非常清晰!
 * 说明：
 * 　坏字符:失配时text当前的字符
 * 　好后缀：失配时已经匹配到的后缀
 * 失配时：
 * 　没有好后缀,移动位置=坏字符在pattern的位置-坏字符在pattern最右出现的位置
 * 　有好后缀,移动位置=好后缀在pattern的位置-好后缀在pattern第一次出现的位置
 *
 * date：2017/8/17
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class BM {

    public static void main(String[] args) {
        String text = "HERE IS A SIMPLE EXAMPLE";
        String pattern = "EXAMPLE";
        System.out.println(bm(text, pattern));

    }

    /**
     * 这个实现没有加入好后缀,
     * 可以先通过这个理解坏字符怎么做,再考虑好后缀去优化,
     * 记住:遍历text的索引永远不回头,只有去和pattern的临时索引会回头
     *
     * @param text    文本串
     * @param pattern 匹配的模式串
     * @return 模式串在文本串出现的位置
     */
    private static int bm0(String text, String pattern) {
        if ((text == null && pattern != null) || (text == null && pattern != null) || text.length() < pattern.length()) {
            return -1;
        }
        int M = text.length();//文本的长度
        int N = pattern.length();//模式的长度
        int I = N - 1;//大I表示遍历text的索引,永远往后,不回头
        while (I < M) {
            int i = I;//代替大I去和模式串比较
            int j = N - 1;//遍历pattern的索引,每次重新比较都要从最后一位开始
            while (j >= 0) {
                char t = text.charAt(i);
                char p = pattern.charAt(j);
                if (t == p) {//相同则从继续往前比较
                    i--;
                    j--;
                } else {
                    //没有好后缀,移动位置=坏字符在pattern的位置-坏字符在pattern最右出现的位置
                    int k = badSuffixPositionOfPattern(pattern, t);
                    I += j - k;//大I往后跳
                }
                break;
            }
            if (j == -1) {//遍历到最后一个,此时j经过--后已经为-1,并且tmpI也多退回了一格,所以往前加1
                return i + 1;
            }
        }
        return -1;//找不到
    }

    /**
     * 记住:遍历text的索引I永远不回头,只有去和pattern的临时索引会回头
     *
     * @param text    文本串
     * @param pattern 匹配的模式串
     * @return 模式串在文本串出现的位置
     */
    private static int bm(String text, String pattern) {
        if ((text == null && pattern != null) || (text == null && pattern != null) || text.length() < pattern.length()) {
            return -1;
        }
        int M = text.length();//文本的长度
        int N = pattern.length();//模式的长度
        int I = N - 1;//大I表示遍历text的索引,永远往后,不回头
        while (I < M) {
            boolean good = false;//是否有好后缀
            int i = I;//代替大I去和模式串比较
            int j = N - 1;//遍历pattern的索引,每次重新比较都要从最后一位开始
            while (j >= 0) {
                char t = text.charAt(i);
                char p = pattern.charAt(j);
                if (t == p) {//相同则从继续往前比较
                    good = true;
                    i--;
                    j--;
                } else {
                    if (good) {
                        //有好后缀,移动位置=好后缀在pattern的位置-好后缀在pattern第一次出现的位置
                        int x = j + 1; //当前字符是不匹配的,pattern从x往后的就是好后缀
                        String goodSuffix = pattern.substring(x, N);
                        I += goodSuffixLastPositionOfPattern(pattern, goodSuffix) - goodSuffixFirstPositionOfPattern(pattern, goodSuffix);
                    } else {
                        //没有好后缀,移动位置=坏字符在pattern的位置-坏字符在pattern最右出现的位置
                        int k = badSuffixPositionOfPattern(pattern, t);
                        I += j - k;//大I往后跳
                    }

                    break;
                }
            }
            if (j == -1) {//遍历到最后一个,此时j经过--后已经为-1,并且tmpI也多退回了一格,所以往前加1
                return i + 1;
            }
        }
        return -1;//找不到
    }

    /**
     * 坏字符出现在pattern中最右的位置
     *
     * @param pattern
     * @param t
     * @return
     */
    private static int badSuffixPositionOfPattern(String pattern, char t) {
        return pattern.lastIndexOf(t);
    }

    /**
     * 找出好后缀在pattern中最后的位置
     * <p>
     * EXAMPLE中,好后缀是E,返回:6 - 0
     * LEXAMPLE中,好后缀时LE,返回:6 - 0
     *
     * @param pattern
     * @param goodSuffix
     * @return
     */
    private static int goodSuffixLastPositionOfPattern(String pattern, String goodSuffix) {
        //好后缀最后出现的位置+长度刚好等于好后缀的位置
        return pattern.lastIndexOf(goodSuffix) + goodSuffix.length();
    }

    /**
     * @return 好后缀第一次在pattern出现的位置
     */
    private static int goodSuffixFirstPositionOfPattern(String pattern, String goodSuffix) {
        return pattern.indexOf(goodSuffix);
    }

}
