package algorithm.other;

/**
 * 判断一个链表是否是循环,快指针和慢指针
 * date：2017/9/2　21:49
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class CircularQueue {

    public static void main(String[] args) {
        Node root = new Node(3);
        root.append(new Node(5))
                .append(new Node(6))
                .append(new Node(1))
                .append(new Node(2))
                .append(new Node(9))
                .append(root);
        //.append(new Node(10));
        System.out.println(isCircular(root));
        System.out.println(circularQueueLength(root));
    }

    private static int circularQueueLength(Node root) {
        Node fast = root;
        Node slow = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.value == slow.value) {
                break;
            }
        }
        int len = 1;
        fast = fast.next;
        while (fast != null && fast.value != slow.value) {
            len++;
            fast = fast.next;
        }
        return len;
    }

    private static boolean isCircular(Node root) {
        Node fast = root;
        Node slow = root;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast.value == slow.value) {
                return true;
            }
        }
        return false;
    }


    private static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }

        public Node append(Node n) {
            this.next = n;
            return n;
        }
    }
}
