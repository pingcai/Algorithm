import java.util.ArrayList;
import java.util.List;

/**
 * Created by pingcai on 2017/5/23.
 * <p>
 * 回溯法基础
 */
public class _77_Combinations {
    public static void main(String[] args) {
        System.out.println(new _77_Combinations().combine(4, 3));
        ;
    }

    private List<List<Integer>> result = new ArrayList();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> tmp = new ArrayList();
        r(n, k, 1, tmp);
        return result;
    }

    public void r(int n, int k, int start, List<Integer> list) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
        }
        for (int i = start; i <= n; i++) {
            list.add(i);
            r(n, k - 1, i + 1, list);
            list.remove(list.size() - 1);//归位
        }
    }
}
