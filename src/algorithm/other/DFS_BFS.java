package algorithm.other;

import java.util.LinkedList;
import java.util.Stack;

/**
 * date：2017/9/7　18:52
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class DFS_BFS {
    /**
     * 　 　　　　9
     * 　　　 ／　＼
     * 　　　８　　　１０
     * 　　　／＼　　　＼
     * 　　６　　５　　１２
     * 　　　　　＼
     * 　　　　　　１6
     * 深度:9 8 6 5 16 10 12
     * 广度:9 8 10 6 5 12 16
     *
     * @param args
     */
    public static void main(String[] args) {
        Node root = new Node(9);
        root.appendLeft(new Node(8)).appendLeft(new Node(6));
        root.appendRight(new Node(10)).appendRight(new Node(12));
        root.left.appendRight(new Node(5)).appendRight(new Node(16));

        bfsRecur(root);

    }

    private static void bfsRecur(Node root) {
        /**
         * 广度优先递归暂时没想到递归的思路
         * 广度优先是层级遍历,用节点做递归貌似无解
         * 可以用队列做递归,但还是队列实现
         */
        if (null != root) {
            LinkedList<Node> nodes = new LinkedList<>();
            nodes.offer(root);
            r(nodes);
        }
    }

    private static void r(LinkedList<Node> nodes) {
        if (!nodes.isEmpty()) {
            Node cur = nodes.pop();
            visit(cur);
            if (null != cur.left) {
                nodes.offer(cur.left);
            }
            if (cur.right != null) {
                nodes.offer(cur.right);
            }
            r(nodes);
        }
    }

    /**
     * 广度优先
     *
     * @param root
     */
    private static void bfs(Node root) {
        if (root != null) {
            LinkedList<Node> list = new LinkedList<>();
            list.push(root);
            Node cur = null;
            while (!list.isEmpty()) {
                cur = list.pop();
                visit(cur);
                if (null != cur.left) {
                    list.offer(cur.left);
                }
                if (null != cur.right) {
                    list.offer(cur.right);
                }
            }
        }
    }

    /**
     * 深度优先(非递归)
     */
    private static void dfs(Node root) {
        if (root != null) {
            Stack<Node> stack = new Stack<>();
            stack.push(root);
            Node cur = null; //当前节点
            while (!stack.empty()) {
                cur = stack.pop();
                visit(cur);
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
            }
        }
    }

    /**
     * 深度优先(递归)
     */
    private static void dfsRecur(Node root) {
        if (root != null) {
            visit(root);
            dfsRecur(root.left);
            dfsRecur(root.right);
        }
    }

    private static void visit(Node root) {
        System.out.print(root.val + " ");
    }


    private static class Node {
        int val;
        Node left;
        Node right;

        public Node(int i) {
            val = i;
        }

        public Node appendLeft(Node node) {
            this.left = node;
            return node;
        }

        public Node appendRight(Node node) {
            this.right = node;
            return node;
        }
    }
}
