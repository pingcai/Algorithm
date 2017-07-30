package algorithm.tree;

/**
 * 二叉平衡搜索树（AVL) 《数据结构与算法·java语言描述》
 * <p>
 * 左左：对节点的左子树插入一个左节点
 * 左右：对节点的左子树插入一个右节点
 * 右左：对节点的右子树插入一个左节点
 * 右右：对节点的右子树插入一个右节点
 * <p>
 * 口诀：左左则右，右右则左，左右左右，右左右左
 * 含义：左左就使用右旋转；右右就使用左旋转；左右就先左旋转，再右旋转；右左就先右旋转在左旋转。
 * 注：旋转是针对枢纽节点
 * <p>
 * date：2017/7/30
 * author：黄平财
 */
public class AVL<AnyType extends Comparable<AnyType>> {

    private AvlNode root;

    public static void main(String[] args) {

        AVL<String> tree = new AVL<String>();

        tree.insert("a");
        tree.insert("b");
        tree.insert("c");
        tree.insert("d");
        tree.insert("e");
        tree.insert("f");
        tree.insert("g");
        tree.delete("d");
        System.out.println(tree);
    }

    /**
     * 高度从0计算
     *
     * @return 树的最大高（深）度
     */
    public int height() {
        return height(root);
    }

    private int height(AvlNode node) {
        return node == null ? -1 : node.height;
    }

    /**
     * 每次都是 this.root 指向新调整的树的root节点
     *
     * @param x
     */
    public void insert(AnyType x) {
        this.root = insert(x, root);
    }

    /**
     * 将 x 插入 node 中
     *
     * @param x
     * @param node
     * @return
     */
    private AvlNode insert(AnyType x, AvlNode node) {
        if (node == null) {
            if (x == null) {// 不能有空节点
                return null;
            }
            return new AvlNode(x);
        }
        int comp = node.element.compareTo(x);
        if (comp > 0) { //比根节点小
            node.left = insert(x, node.left);
            if (height(node.left) - height(node.right) == 2) {
                if (node.left.element.compareTo(x) > 0) {
                    node = right_rotation(node);
                } else {
                    node = left_right_rotation(node);
                }
            }
        } else if (comp < 0) { // 比根节点大
            node.right = insert(x, node.right);
            if (height(node.right) - height(node.left) == 2) {
                if (node.right.element.compareTo(x) < 0) {
                    node = left_rotation(node);
                } else {
                    node = right_left_rotation(node);
                }
            }
        } else {
            // 插入项重复
            node.element = x;
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    /**
     * 删除指定节点
     *
     * @param x
     */
    public void delete(AnyType x) {
        this.root = delete(this.root, x);
    }

    private AvlNode delete(AvlNode node, AnyType x) {
        if (node == null || x == null) {
            return node;
        }
        int comp = node.element.compareTo(x);
        if (comp > 0) { //x在node左边
            node.left = delete(node.left, x);
            if (height(node.left) - height(node.right) == 2) { // 删除后不平衡，左子树比右子树高，左旋转
                if (node.left.element.compareTo(x) > 0) {
                    node = right_rotation(node);//左左的情况，通过一次右旋转解决
                } else {
                    node = left_right_rotation(node);
                }
            }
        } else if (comp < 0) {
            node.right = delete(node.right, x);
            if (height(node.right) - height(node.left) == 2) {
                if (node.right.element.compareTo(x) > 0) {
                    node = left_rotation(node);
                } else {
                    node = right_left_rotation(node);
                }
            }
        } else { // 当期那节点就是要删除的目标节点
            if (node.left != null && node.right != null) { //如果两棵子树都不为空
                if (height(node.left) > height(node.right)) {
                    AvlNode max = maxNodeOf(node.left);
                    node.element = max.element;
                    node.left = delete(node.left, max.element);
                } else {
                    AvlNode min = minNodeOf(node.right);
                    node.element = min.element;
                    node.right = delete(node.right, min.element);
                }
            } else {
                node = node.left == null ? node.right : node.left;
            }
        }
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
        return node;
    }

    private AvlNode minNodeOf(AvlNode node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            return node;
        }
        return minNodeOf(node.left);
    }

    private AvlNode maxNodeOf(AvlNode node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            return node;
        }
        return maxNodeOf(node.right);
    }

    /**
     * 左左（k1右旋转,枢纽是k1节点）:
     * 　　k2
     * 　／
     * 　k1
     * ／
     * k3
     * 变成：
     * 　　　k1
     * 　　/　\
     * 　k3　　k2
     * 或是：
     * 　　　　　　k2
     * 　　　　　/　\
     * 　　　　k1  z
     * 　　　/ \
     * 　　x　　y
     * 　/
     * s
     * 变成：
     * 　　　k1
     * 　　/　\
     * 　x　　k2
     * /　　/ 　\
     * s　 y　　z
     *
     * @param node 目标节点，其左节点是枢纽
     * @return
     */
    private AvlNode right_rotation(AvlNode node) {
        AvlNode pivot = node.left;
        node.left = pivot.right;
        pivot.right = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }

    /**
     * 右右（左旋转,枢纽是b节点）:
     * 　　a
     * 　　　\
     * 　　　 b
     * 　　　　\
     * 　　　　 c
     *
     * @param node
     * @return
     */
    private AvlNode left_rotation(AvlNode node) {
        AvlNode pivot = node.right;
        node.right = pivot.left;
        pivot.left = node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        pivot.height = Math.max(height(pivot.left), height(pivot.right)) + 1;
        return pivot;
    }


    /**
     * 左右（枢纽k1先左旋转，k2再右旋转）：
     * 　　　　　k3
     * 　　　　/　\
     * 　　　k1　 d
     * 　　/　\
     * 　a 　 k2
     * 　　　/　\
     * 　　b　　c
     * 枢纽k1进行一次左旋转，变成：
     * 　　　　　k3
     * 　　　　/　\
     * 　　　k2　 d
     * 　　/　\
     * 　 k1 　c
     * 　/　\
     * a　　b
     * 再对枢纽k2进行一次右旋转，变成：
     * 　　　　k2
     * 　　　/　　\
     * 　　k1　　　k3
     * 　/　\　　/　\
     * a　　b　c　　d
     *
     * @param k3 根节点
     * @return 调整后的根节点
     */
    private AvlNode left_right_rotation(AvlNode k3) {
        k3.left = left_rotation(k3.left);
        return right_rotation(k3);
    }

    /**
     * @param k3
     * @return
     */
    private AvlNode right_left_rotation(AvlNode k3) {
        k3.right = right_rotation(k3.right); // 左左的情况，右旋
        return left_rotation(k3);
    }

    class AvlNode {

        AvlNode left;
        AvlNode right;
        AnyType element;
        int height;//高度，取左右节点的最大值
        // int N; //子节点个数，可以向BST一样实现

        public AvlNode(AnyType element) {
            this(element, null, null);
        }

        public AvlNode(AnyType element, AvlNode left, AvlNode right) {
            this.left = left;
            this.right = right;
            this.element = element;
            this.height = 0;
        }

        @Override
        public String toString() {
            return "AvlNode{" +
                    "element=" + element +
                    '}';
        }
    }
}
