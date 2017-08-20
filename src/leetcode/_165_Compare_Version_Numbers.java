package leetcode;

/**
 * reference:https://leetcode.com/problems/compare-version-numbers/description/
 * date：2017/8/20　22:15
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class _165_Compare_Version_Numbers {

    public static void main(String[] args) {
        String version1 = "2.6";
        String version2 = "2.87";

        System.out.println(new _165_Compare_Version_Numbers().compareVersion(version1, version2));
    }

    /**
     * 分段比较
     * @param version1
     * @param version2
     * @return
     */
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        int len = Math.max(s1.length,s2.length);
        for(int i=0;i<len;i++){
            int c1 = s1.length<=i?0:Integer.parseInt(s1[i]);
            int c2 = s2.length<=i?0:Integer.parseInt(s2[i]);
            if(c1>c2){
                return 1;
            }else if(c1<c2){
                return -1;
            }
        }
        return 0;
    }

}
