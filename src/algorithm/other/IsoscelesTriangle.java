package algorithm.other;

/**
 * 打印等腰三角形:思路和二叉树差不多,只不过等腰三角形是每次+2,二叉树是乘2
 * date：2017/9/4　16:44
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class IsoscelesTriangle {
    public static void main(String[] args) {
        int level = 8;
        char[][] arr = createIsoscelesTriangle(level);
        for (int i = 0; i < level; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                System.out.print(arr[i][j] == '*' ? '*' : ' ');
            }
            System.out.println();
        }
    }

    /**
     * 从中间往外填充 * 号
     * @param level 高度
     * @return
     */
    private static char[][] createIsoscelesTriangle(int level) {
        int len = 2 * level - 1;
        char[][] arr = new char[level][len];
        int mid = len >> 1;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            count = i;
            while (count > -1) {
                arr[i][mid + count] = '*';
                arr[i][mid - count] = '*';
                count--;
            }
        }
        return arr;
    }
}
