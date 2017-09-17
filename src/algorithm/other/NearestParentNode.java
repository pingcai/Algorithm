package algorithm.other;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

/**
 * 三种情况：
 * 1.有父节点，通过父节点转成链表
 * 2.二叉平衡搜索树：找到一个点，刚好大于a而小于b
 * 3.正常：转成链表，遍历
 * <p>
 * <p>
 * date：2017/9/17　22:34
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class NearestParentNode {
    public static void main(String[] args) {

        Node root = new Node(20);

        Node n1 = new Node(16);
        Node n2 = new Node(7);
        Node n3 = new Node(22);
        Node n4 = new Node(30);
        Node n5 = new Node(70);
        Node n6 = new Node(2);

        Node a = new Node(12);
        Node b = new Node(56);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n2.left = n4;
        n2.right = n5;
        n4.right = n6;
        n6.left = b;
        n3.right = a;
        /**
         *　　 　　　　20
         *          ／＼
         *        １６　　７
         *       ／　 　／ ＼
         *    ２２　 　３０ 　７０
         *              ＼    \
         *               ２ 　 a
         *              ／
         *             ｂ
         */
        System.out.println(new NearestParentNode().find(root, a, b));
    }

    private Node find(Node root, Node a, Node b) {
        if (root != null) {
            ArrayDeque<Node> stack1 = new ArrayDeque<>();
            findPath(stack1, root, a);
            ArrayDeque<Node> stack2 = new ArrayDeque<>();
            findPath(stack2, root, b);
            return cmpare(stack1, stack2);
        }
        return null;
    }

    private boolean findPath(ArrayDeque<Node> stack, Node root, Node a) {
        if (root != null) {
            stack.add(root);
            if (root.val == a.val) {
                return true;
            } else {
                if (findPath(stack, root.left, a)) {
                    return true;
                }
                if (findPath(stack, root.right, a)) {
                    return true;
                }
            }
        }
        //根节点要保留
        if (root != null && stack.size() > 1) {
            stack.removeLast();
        }
        return false;
    }

    private Node cmpare(ArrayDeque<Node> stack1, ArrayDeque<Node> stack2) {
        Node node = null;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            if (stack1.peek().val == stack2.pop().val) {
                node = stack1.pop();
            } else {
                break;
            }
        }
        return node;
    }
}

class Node {

    int val;
    Node left;
    Node right;

    public Node(int x) {
        val = x;
    }

    public String toString() {
        return String.valueOf(val);
    }
}
