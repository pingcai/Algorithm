package algorithm.tree;

import java.util.NoSuchElementException;

/**
 * 实现的基础是2-3查找树；
 *　
 * 2：正常二叉树的节点；一个节点有一个key和一个value，同时有两个子节点；
 *　
 * 3：如果左节点是红色，可以与父节点连成一体，则父节点视为3节点；一个节点可以有2个key和两个value，同时有三个子节点；
 *　
 * 所有新插入的节点都是红色，大写为黑色，小写为红色，delete部分来源于《算法》课后练习；
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
     * 1.2.k>n，插入右边（不合法），然后左旋：
     * 　　　N
     * 　　／＼
     * 　 　　k
     * <p>
     * 再旋转：
     * 　　　K
     * 　　／＼
     * 　 n
     * <p>
     * 2.插入３－节点（即当前节点的左节点是红色的），有三种情况:小于V，介于K和V之间，大于K：
     * 　　　K
     * 　　／＼
     * 　 v
     *　2.1.小于V：
     * 　　　K
     * 　　／＼
     * 　 v
     * 　/
     * n
     * 有连续的两个红节点不合法，旋转：
     * 　　　V
     * 　　／＼
     * 　 n　　k
     * 　
     * 2.2.介于K和v之间：
     * 　　　K
     * 　　／＼
     * 　 v
     * 　　＼
     *     n
     * ｎ位于ｖ的右节点且ｎ为红色，不合法，旋转：
     * 　　　K
     * 　　／＼
     *  　ｎ
     *　 ／
     * ｖ
     * 有连个连续的红节点，仍不合法，旋转K：
     * 　　　N
     * 　　／＼
     * 　 v　　k
     * 2.2.大于K，插入右边：
     * 　　　K
     * 　　／＼
     * 　 v　　n
     * K有红子节点，不合法，变色：
     * 　　　k
     * 　　／＼
     * 　 V　　N
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
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }
        node.N = 1 + size(node.left) + size(node.right);
        return node;
    }

    /**
     * 左旋，被旋转的点会变成红色，旋转上来的点保持被旋转点的颜色
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

    private Node delete(Node h, Key key) {
        // assert get(h, key) != null;

        if (key.compareTo(h.key) < 0)  {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = delete(h.left, key);
        }
        else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (key.compareTo(h.key) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (key.compareTo(h.key) == 0) {
                Node x = min(h.right);
                h.key = x.key;
                h.value = x.value;
                // h.val = get(h.right, min(h.right).key);
                // h.key = min(h.right).key;
                h.right = deleteMin(h.right);
            }
            else h.right = delete(h.right, key);
        }
        return balance(h);
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMin(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the minimum key rooted at h
    private Node deleteMin(Node h) {
        if (h.left == null)
            return null;

        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);

        h.left = deleteMin(h.left);
        return balance(h);
    }


    /**
     * Removes the largest key and associated value from the symbol table.
     * @throws NoSuchElementException if the symbol table is empty
     */
    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("BST underflow");

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // delete the key-value pair with the maximum key rooted at h
    private Node deleteMax(Node h) {
        if (isRed(h.left))
            h = rotateRight(h);

        if (h.right == null)
            return null;

        if (!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return balance(h);
    }

    /**
     * Removes the specified key and its associated value from this symbol table
     * (if the key is in this symbol table).
     *
     * @param  key the key
     * @throws IllegalArgumentException if {@code key} is {@code null}
     */
    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(key)) return;

        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, key);
        if (!isEmpty()) root.color = BLACK;
        // assert check();
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    public boolean contains(Key key) {
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.value;
        }
        return null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h) {
        // assert (h != null);
        // assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    /**
     * Returns the smallest key in the symbol table.
     * @return the smallest key in the symbol table
     * @throws NoSuchElementException if the symbol table is empty
     */
    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("called min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
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
        tree.put("s", "");
        tree.put("e", "");
        tree.put("a", "");
        tree.put("r", "");
        tree.put("c", "");
        tree.put("h", "");
        tree.put("x", "");
        tree.put("m", "");
        tree.put("p", "");
        tree.put("l", "");

        System.out.println(tree);
    }
}
