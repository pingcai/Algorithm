package algorithm.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉搜索树（Binary Search Tree）
 * date：2017/7/28
 * author：黄平财
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;


    /**
     * 递归放置节点
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node node, Key key, Value value) {
        if (node == null) {
            Node root = new Node(key, value);
            root.N = 1;
            return root;
        }
        int comp = node.key.compareTo(key);
        if (comp > 0) {
            node.left = put(node.left, key, value);
            node.N = size(node.left) + size(node.right) + 1;
        } else if (comp < 0) {
            node.right = put(node.right, key, value);
            node.N = size(node.left) + size(node.right) + 1;
        } else {
            node.value = value;
        }
        return node;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node node, Key key) {
        //递归
        if (node == null) {
            return null;
        }
        int comp = node.key.compareTo(key);
        if (comp > 0) {
            return get(node.left, key);
        } else if (comp < 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    public void deleteMin() {
        this.root = deleteMin(this.root);
    }

    private Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right; //使根节点的左节点指向右节点
        node.left = deleteMin(node.left);
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public void delete(Key key) {
        this.root = delete(this.root, key);
    }

    /**
     * 当前节点就是目标节点，有四种情况：
     * 1.当前节点为空
     * 2.左子树为空 -> 使当前节点的父节点指向右节点
     * 3.右子树为空 -> 使当前节点的父节点指向左节点
     * 4.左右不为空 -> 从右子树中选出最小的节点替换当前节点
     */
    private Node delete(Node node, Key key) {
        if (node == null) return null;
        int comp = node.key.compareTo(key);
        if (comp > 0) { // 目标节点在左边
            node.left = delete(node.left, key);
        } else if (comp < 0) {
            node.right = delete(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node tmp = node;
            node = min(root.right);
            node.right = deleteMin(tmp.right);
            node.left = tmp.left;
        }
        node.N = size(node.left) + size(node.right) + 1;
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        return node == null ? 0 : node.N;
    }

    /**
     * @param key
     * @return node.key为key的节点，或node.key最小接近key的节点
     */
    public Node floor(Key key) {
        return floor(root, key);
    }

    /**
     * 向下取整：
     * 1.比较当前节点node的key和指定的key;
     * 2.如果node.key 大于 key，说明小于等于key的节点一定在左子树中；
     * 3.如果node.key 小于 key，说明小于等于key的节点必定在node或node的右节点中；
     * 4.查找node的右子树，如果存在，则返回，如果不存在则返回node；
     *
     * @param node
     * @param key
     * @return
     */
    private Node floor(Node node, Key key) {
        if (node == null) return null;
        int comp = node.key.compareTo(key);
        if (comp == 0) {
            return node;
        } else if (comp > 0) {//节点小于比较的节点
            return floor(node.left, key);
        }
        Node r = floor(node.right, key);//查找右节点有没有小于key的节点
        if (r != null) {
            return r;
        }
        return node; //当前节点
    }

    /**
     * 向上取整刚好与向下取整相反
     *
     * @param key
     * @return
     */
    public Node ceiling(Key key) {
        return ceiling(root, key);
    }

    private Node ceiling(Node node, Key key) {
        if (node == null) return null;
        int comp = node.key.compareTo(key);
        if (comp == 0) {
            return node;
        } else if (comp < 0) {
            return ceiling(node.right, key);
        }
        Node l = ceiling(node.left, key);
        if (l != null) return l;
        return node;
    }

    /**
     * 取排名为 k 的节点
     *
     * @param k 下标，从 0 开始
     * @return
     */
    public Node select(int k) {
        return select(root, k);
    }

    private Node select(Node node, int k) {
        if (node == null) return null;
        int n = size(node.left);//子节点数
        if (n < k) {
            return select(node.right, k - n - 1);
        } else if (n > k) {
            return select(node.left, k);
        }
        return node;
    }

    /**
     * 求指定key在BST中的索引
     *
     * @param key
     * @return
     */
    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null || this.get(key) == null) return -1;
        int comp = node.key.compareTo(key);
        if (comp > 0) { // node节点的key大于key
            return rank(node.left, key);
        } else if (comp < 0) {
            return 1 + size(node.left) + rank(node.right, key);
        }
        return size(node.left);
    }

    /**
     * 中序遍历求顺序序列
     * @return
     */
    public List<Node> list() {
        ArrayList<Node> list = new ArrayList<Node>();
        list(root, list);
        return list;
    }

    private void list(Node node, ArrayList<Node> nodes) {
        if (node != null) {
            list(node.left,nodes);
            nodes.add(node);
            list(node.right,nodes);
        }
    }

    public Node max() {
        return max(root);
    }

    private Node max(Node node) {
        if (node == null) return null;
        return node.right == null ? node : max(node.right);
    }

    public Node min() {
        return min(root);
    }

    private Node min(Node node) {
        if (node == null) return null;
        return node.left == null ? node : min(node.left);
    }

    class Node {
        Node left;
        Node right;
        int N;//子节点个数
        Key key;
        Value value;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }
}

class Test {
    public static void main(String[] args) {
        BST<String, String> bst = new BST<>();

        String value = "value";

        bst.put("h", value);
        bst.put("a", value);
        bst.put("c", value);
        bst.put("q", value);

        System.out.println(bst.list());

    }
}
