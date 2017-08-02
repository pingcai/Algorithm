package algorithm.tree;

/**
 * 实现的基础是2-3查找树；
 *　
 * 2：一个节点有一个key和一个value，同时有两个子节点；
 *　
 * 3：一个节点可以有2个key和两个value，同时有三个子节点；
 *　
 * 在左节点是红色的情况下，父节点视为3节点；
 *　
 * 所有新插入的节点都是红色，大写为红色，小写为黑色；
 *　
 * 如：
 * 　　Ｎ
 * 　／　＼
 * 是２－节点；
 * 　
 * 插入ｈ之后：
 * 　　Ｎ
 * 　／　＼
 * ｈ
 * 就变成了3节点：
 *　
 * 五个性质：
 * <p>
 * 　1.节点非红即黑；
 * 　2.根节点为黑色；
 * 　3.叶子节点（NULL）为黑色；
 * 　4.一条树枝上不能有连续的红节点；
 * 　5.任一节点到其叶子节点的黑节点总数都相等；
 * <p>
 * date：2017/8/2
 * author：黄平财
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public void put(Key k, Value v) {
        root = put(root, k, v);
        root.color = BLACK;//根节点为黑色
    }

    /**
     * <p>
     * 插入分为四种情况：
     * <p>
     * 1.插入２－节点，设插入的节点为 k
     * 　　N
     * 　／＼
     * <p>
     * 1.1.k<n插入左边即可：
     * 　　　N
     * 　　／＼
     * 　 k
     * <p>
     * 1.2.k>n，插入右边，然后左旋：
     * 　　　N
     * 　　／＼
     * 　 　　k
     * <p>
     * 再旋转：
     * 　　　K
     * 　　／＼
     * 　 n
     * <p>
     * 2.插入３－节点：
     *
     *
     *
     *
     * @param node
     * @param k
     * @param v
     * @return
     */
    private Node put(Node node, Key k, Value v) {
        if (null == node) {
            return new Node(k, v, 1, RED);//所有新插入的节点都是红色的
        }

        int comp = node.key.compareTo(k);

        if (comp > 0) {// 插入左边
            node.left = put(node.left, k, v);
        } else if (comp < 0) {
            node.right = put(node.right, k, v);
        } else {
            node.value = v;//key相同，替换值
        }

        if (!isRed(node.left) && isRed(node.right)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node.left);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * 左旋
     *
     * @param node
     * @return
     */
    public Node rotateLeft(Node node) {
        Node tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        tmp.color = node.color;
        node.color = RED;//置为红色
        node.N = 1 + size(node.left) + size(node.right);
        return tmp;
    }

    public Node rotateRight(Node node) {
        Node tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        tmp.color = node.color;
        node.color = RED;//置为红色
        node.N = 1 + size(node.left) + size(node.right);
        return tmp;
    }

    public void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private int size(Node node) {
        return node == null ? 0 : node.N;
    }

    public boolean isRed(Node node) {
        return node == null ? false : node.color == RED;
    }


    class Node {
        Node left;
        Node right;
        Key key;
        Value value;
        int N;

        boolean color;

        public Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            N = n;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", N=" + N +
                    '}';
        }
    }
}

class RedBlackBSTTest {
    public static void main(String[] args) {
        RedBlackBST<String, String> tree = new RedBlackBST<>();
        tree.put("a", "a");
        tree.put("b", "a");
        tree.put("c", "a");
        tree.put("d", "a");
        tree.put("e", "a");
        tree.put("f", "a");
        tree.put("g", "a");
        tree.put("h", "a");
        tree.put("i", "a");
        tree.put("j", "a");

        System.out.println(tree);
    }
}
