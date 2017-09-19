package algorithm.other;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 使用1G的内存：-Xms1024M -Xmx1024M  -Xmn800M -XX:MaxMetaspaceSize=16M
 * CPU：core(TM)i5-4200H 2.8Ghz
 * 硬盘：西部数据机械1T 5200转
 * 2千万单线程耗时36秒
 * 2亿单线程消耗736秒，十二分钟左右,文件2G
 * 20亿耗时37632秒（10多个小时），文件22G，需要切割成100个文件，每个文件234M
 * date：2017/9/10　12:06
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class OuterSort {

    String sourcePath;

    String tempPath;

    String sortedPath;

    String resultPath;

    int perSize;

    public OuterSort(String source, String temp, String sortedPath, String result, int perSize) {
        this.sourcePath = source;
        this.tempPath = temp;
        this.sortedPath = sortedPath;
        this.resultPath = result;
        this.perSize = perSize;
    }

    public static void main(String[] args) throws Exception {

        String source = "d://data//numbers.txt";
        String temp = "d://data//temp";
        String sorted = "d://data//sorted";
        String result = "d://data/result.txt";
        //每个文件存放数据量
        int perSize = 20000000;
        //int perSize = 100000;

        // 创建源文件，20亿个整型数
        //crateData(source, 2000000000);

        new OuterSort(source, temp, sorted, result, perSize).doIt();

    }

    private static void crateData(String source, long size) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(source));) {
            Random random = new Random(Integer.MAX_VALUE);
            for (int i = 0; i < size; i++) {
                writer.write(random.nextInt() + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 暴力+归并
     * 现将文件切分成内存可以装下的n个文件
     * 再一次遍历这n个文件
     */
    public void doIt() throws Exception {

        long start = System.currentTimeMillis();

        // 1.切割文件
        File source = new File(this.sourcePath);
        List<File> tempFiles = splitFile(source);

        System.out.println(String.format("文件切割完成，共%s个文件，耗时：%d秒", tempFiles.size(), (System.currentTimeMillis() - start) / 1000));

        // 2.单个文件排序
        //多线程
        //multiThreadDeal(tempFiles);
        simpleDeal(tempFiles);

        System.out.println(String.format("排序完成，耗时：%d秒", (System.currentTimeMillis() - start) / 1000));

        // 3.归并排序，写入同一个文件
        mergeAll(Arrays.asList(new File(sortedPath).listFiles()));

        // 删除中间文件
        Stream.of(new File(tempPath).listFiles()).forEach((file) -> file.delete());
        Stream.of(new File(sortedPath).listFiles()).forEach((file) -> file.delete());

        System.out.println(String.format("总耗时：%d秒", (System.currentTimeMillis() - start) / 1000));
    }

    /**
     * 取数组中的最小值，并将最小值的位置放置在AtomicInteger中
     *
     * @param empty
     * @param tmp
     * @param maxPos
     * @return
     */
    private static int minInteger(boolean[] empty, int[] tmp, AtomicInteger maxPos) {
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < tmp.length; i++) {
            if (!empty[i] && max >= tmp[i]) {
                maxPos.set(i);
                max = tmp[i];
            }
        }
        return max;
    }

    /**
     * 判断所有的分组都遍历到结尾
     *
     * @param empty
     * @return
     */
    private static boolean allEmpty(boolean[] empty) {
        for (int i = 0; i < empty.length; i++) {
            if (!empty[i]) {
                return false;
            }
        }
        return true;
    }

    public static Integer[] sort(List<Integer> list) {
        Integer[] nums = new Integer[list.size()];
        list.toArray(nums);
        list.clear();// 清空，释放内存
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static void mergeSort(Integer[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(Integer[] arr, int l, int m, int r) {
        int[] tmpArr = new int[r - l + 1];//临时数组
        int i = 0;//临时数组排序过程中的索引
        int j = l;//原数组前半部分的索引
        int k = m + 1;//原数组后半部分的索引
        while (j <= m && k <= r) {
            //从两个数组中取出最小的放入中间数组
            if (arr[j] <= arr[k]) {
                tmpArr[i++] = arr[j++];
            } else {
                tmpArr[i++] = arr[k++];
            }
        }
        //剩余部分依次放入中间数组
        while (j <= m) tmpArr[i++] = arr[j++];
        while (k <= r) tmpArr[i++] = arr[k++];
        //将中间数组中的内容复制回原数组
        i = 0;
        j = 0;
        while (i < tmpArr.length) arr[j++] = tmpArr[i++];
    }



    // 单线程处理
    public void simpleDeal(List<File> tempFiles) throws IOException {
        for (int i = 0; i < tempFiles.size(); i++) {
            deal(tempFiles.get(i), i);
        }
    }

    //多线程,多核CPU有效
    public void multiThreadDeal(List<File> tempFiles) throws InterruptedException {
        Thread[] threads = new Thread[tempFiles.size()];
        for (int i = 0; i < tempFiles.size(); i++) {
            final File sub = tempFiles.get(i);
            final int j = i;
            Thread t = new Thread(() -> {
                try {
                    deal(sub, j);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threads[i] = t;
            t.start();
        }

        for (int i = 0; i < tempFiles.size(); i++) {
            threads[i].join();
        }
    }

    /**
     * @param list 所有文件
     */
    private void mergeAll(List<File> list) throws FileNotFoundException {
        boolean[] empty = new boolean[list.size()];
        List<LinkedList<String>> part = new ArrayList<>();
        List<LineNumberReader> readers = new ArrayList<>();
        for (File file : list) {
            part.add(new LinkedList<>());
            readers.add(new LineNumberReader(new FileReader(file)));
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultPath))) {
            int min = 0;
            int i = 0;
            while (!allEmpty(empty)) {
                min = min(part, readers, empty);
                if (!allEmpty(empty)) {
                    writer.write(min + "\r\n");// 真正写入文件的
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param part    每个文件读到的行数，用队列实现，当队列没有数时再磁盘取（可解决需要读取文件某行的问题）
     * @param readers
     * @param empty   是否读到尽头
     * @return
     */
    private int min(List<LinkedList<String>> part, List<LineNumberReader> readers, boolean[] empty) throws IOException {
        int[] tmp = new int[part.size()];
        LineNumberReader reader = null;
        LinkedList<String> curPart = null;//当前部分
        String cur = null; // 当前部分的首位元素
        for (int j = 0; j < part.size(); j++) {
            curPart = part.get(j);
            if (!empty[j] && curPart.isEmpty()) {
                reader = readers.get(j);
                curPart.offer(reader.readLine());
            }
            cur = curPart.getFirst();
            if (cur == null) {
                empty[j] = true;
                continue;
            }
            tmp[j] = Integer.parseInt(curPart.getFirst()); // 取第一个
        }

        //只取第一个
        AtomicInteger minPos = new AtomicInteger(0);
        int min = minInteger(empty, tmp, minPos);
        part.get(minPos.intValue()).removeFirst();

        return min;
    }

    // 读取文件并排序排序
    private void deal(File f, int i) throws IOException {
        List<Integer> list = new ArrayList<>();
        try (FileInputStream in = new FileInputStream(f);
             BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {
            Stream<String> stream = reader.lines();
            stream.forEach(s->{
                list.add(Integer.parseInt(s)); // 读入所有文件
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        Integer[] nums = new Integer[list.size()];
        list.toArray(nums);
        list.clear(); // GC回收
        Arrays.sort(nums);
        //Integer[] nums = sort(list); // 自己实现的归并排序
        write2File(nums, i);
    }

    private void write2File(Integer[] list, int i) throws IOException {
        File file = new File(sortedPath, i + ".txt");
        file.createNewFile();
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"))) {
            for (Integer line : list) {
                writer.write(line + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 切割文件
    private List<File> splitFile(File source) throws IOException {

        List<File> list = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(source));
        String number = null;
        int i = 0;
        int j = 0;

        File tempFile = new File(
                new StringBuilder(tempPath).append(File.separator).append(i++).append(".txt").toString());
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(tempFile));
            list.add(tempFile);
            while ((number = reader.readLine()) != null) {
                if (j % perSize == 0 && j / perSize > 0) {
                    tempFile = new File(
                            new StringBuilder(tempPath).append(File.separator).append(i++).append(".txt").toString());
                    list.add(tempFile);
                    writer.flush();
                    writer.close();
                    writer = new BufferedWriter(new FileWriter(tempFile));
                }
                j++;
                writer.write(number + "\r\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
        return list;
    }
}
