package leetcode;

/**
 * Created by pingcai on 2017/7/21.
 */
public class _13_Roman_to_Integer {
    public static void main(String[] args){
        String s = "";
        System.out.println(new _13_Roman_to_Integer().romanToInt(s));
    }

    /**
     * 罗马数字：https://segmentfault.com/a/1190000002683379
     * 代码引用：https://discuss.leetcode.com/topic/821/my-solution-for-this-question-but-i-don-t-know-is-there-any-easier-way/75
     * @param s
     * @return
     */
    public int romanToInt(String s) {
        int sum=0;
        if(s.indexOf("IV")!=-1){sum-=2;}
        if(s.indexOf("IX")!=-1){sum-=2;}
        if(s.indexOf("XL")!=-1){sum-=20;}
        if(s.indexOf("XC")!=-1){sum-=20;}
        if(s.indexOf("CD")!=-1){sum-=200;}
        if(s.indexOf("CM")!=-1){sum-=200;}

        char c[]=s.toCharArray();
        int count=0;

        for(;count<=s.length()-1;count++){
            if(c[count]=='M') sum+=1000;
            if(c[count]=='D') sum+=500;
            if(c[count]=='C') sum+=100;
            if(c[count]=='L') sum+=50;
            if(c[count]=='X') sum+=10;
            if(c[count]=='V') sum+=5;
            if(c[count]=='I') sum+=1;

        }

        return sum;

    }
}
