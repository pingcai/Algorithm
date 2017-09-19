package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1.从下往上递归
 * date：2017/9/19　17:03
 * author：黄平财
 * mail:hpingcai@gmail.com
 */
public class _257_Binary_Tree_Paths {
    public static void main(String[] args) {

        /*
        * 　　　１０
        *　　　　／＼
        * 　　　３　４
        * 　　／＼
        * 　９　　１２
        *
        *
        * */

        TreeNode root = new TreeNode(10);
        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(9);
        TreeNode n4 = new TreeNode(12);

        root.left = n1;
        root.right = n2;
        n1.left = n3;
        n1.right = n4;

        System.out.println(new _257_Binary_Tree_Paths().binaryTreePaths(root));
    }


    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        r(list, "", root);
        return list;
    }

    private void r(List<String> strings, String s, TreeNode root) {
        if (root != null) {
            if (root.left == null && root.right == null) {
                strings.add(s + root.val);
            }
            if (root.left != null) {
                r(strings, s + String.valueOf(root.val) + "->", root.left);
            }
            if (root.right != null) {
                r(strings, s + String.valueOf(root.val) + "->", root.right);
            }
        }
    }

    public List<String> binaryTreePaths1(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        if (root.left == null && root.right == null) {
            list.add(String.valueOf(root.val));
            return list;
        }
        for (String s : binaryTreePaths(root.left)) {
            list.add(root.val + "->" + s);
        }
        for (String s : binaryTreePaths(root.right)) {
            list.add(root.val + "->" + s);
        }
        return list;
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

