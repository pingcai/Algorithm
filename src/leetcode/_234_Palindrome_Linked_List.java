package leetcode;

public class _234_Palindrome_Linked_List {

    public static void main(String[] args) {
        ListNode root = new ListNode(1);
        ListNode root2 = new ListNode(1);
        ListNode root3 = new ListNode(2);
        ListNode root4 = new ListNode(2);
        ListNode root5 = new ListNode(1);

        root.next = root2;
        root2.next = root3;
        root3.next = root4;
        root4.next=root5;


        System.out.println(new _234_Palindrome_Linked_List().isPalindrome(root2));

    }

    public boolean isPalindrome(ListNode head) {
        int len = 0;
        ListNode mid = head;
        while (mid != null) { //长度
            len++;
            mid = mid.next;
        }
        if (len == 0 || len == 1) {
            return true;
        } else {
            len = (len + 1) / 2;
        }

        mid = head;
        while (len > 0) { //到中间
            len--;
            mid = mid.next;
        }
        ListNode pre=null;
        ListNode next=null;
        while (mid != null) {
            next = mid.next;
            mid.next=pre;
            pre=mid;
            mid=next;
        }

        //pre最终指向逆置后的头结点
        while (head != null && pre != null) {
            if (head.val == pre.val) {
                head = head.next;
                pre = pre.next;
            } else {
                return false;
            }
        }

        return true;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}


