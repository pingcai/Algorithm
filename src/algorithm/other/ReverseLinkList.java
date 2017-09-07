package algorithm.other;

/**
 * 链表翻转
 * date：2017/9/8　0:15
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class ReverseLinkList {

    public static void main(String[] args) {
        Node root = new Node(1);
        root.append(new Node(2))
                .append(new Node(3))
                .append(new Node(4))
                .append(new Node(5))
                .append(new Node(6));
        System.out.println(root.toString());
        root = reverse(root);
        System.out.println(root.toString());
    }

    /**
     * 1 2 3 4 5
     *
     * @param root
     * @return
     */
    private static Node reverse(Node root) {
        Node pre = root;
        if (root != null) {
            root = root.next;
            pre.next = null; // 头结点的next置为空
            Node next = null;
            while (root != null) {
                next = root.next;
                root.next = pre;
                pre = root;
                root = next;
            }
        }
        return pre;
    }


    private static class Node {
        int val;
        Node next;

        public Node(int i) {
            val = i;
        }

        public Node append(Node node) {
            this.next = node;
            return node;
        }

        @Override
        public String toString() {
            return val + " " + (next == null ? "" : next.toString());
        }
    }
}
