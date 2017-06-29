import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by pingcai on 2017/5/23.
 */
public class _90_Subsets_II {
    public static void main(String[] args) {

        int[] arr = {4, 4, 4, 1, 4};

        System.out.println(new _90_Subsets_II().subsetsWithDup_new(arr));
    }

    private List<List<Integer>> result = new ArrayList<>();

    //回溯,是77题的变种
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> list = new ArrayList<>();
        int start = 0;
        Arrays.sort(nums);
        for (int size = 0; size <= nums.length; size++) {
            backtrace(size, nums, start, list);
        }
        return result;
    }

    private void backtrace(int size, int[] nums, int start, List<Integer> list) {
        if (size == 0) {
            List l = new ArrayList<>(list);
            if (!result.contains(l)) {
                result.add(l);
            }
        } else {
            for (int i = start; i < nums.length; i++) {
                list.add(nums[i]);
                backtrace(size - 1, nums, i + 1, list);
                list.remove(list.size() - 1);
            }

        }

    }

    public List<List<Integer>> subsetsWithDup_new(int[] nums) {
        List<Integer> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrace_new(nums,0,list);
        return result;
    }
    // 大牛的解法,省略 判断步骤
    private void backtrace_new(int[] nums, int start, List<Integer> list) {
        result.add(new ArrayList<>(list));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            list.add(nums[i]);
            backtrace_new(nums, i + 1, list);
            list.remove(list.size() - 1);
        }
    }

}
