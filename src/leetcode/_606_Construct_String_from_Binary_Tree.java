package leetcode;

import java.lang.StringBuilder;
import java.util.LinkedList;
//Definition for a binary tree node.
class TreeNode {
 int val;
 TreeNode left;
 TreeNode right;
 TreeNode(int x) { val = x; }
}

/**
*       1
*     /   \
*    2     3
*   /    
*  4     
*Output: "1(2(4))(3)"
**/
public class _606_Construct_String_from_Binary_Tree {
    public static void main(String[] ar){

    	TreeNode root = new TreeNode(1);
    	TreeNode root2 = new TreeNode(2);
    	TreeNode root3 = new TreeNode(3);
    	TreeNode root4 = new TreeNode(4);

/*    	root.left = root2;
    	root2.left=root4;
    	root.right=root3;*/

		root.left = root2;
    	root.right=root3;

    	root2.right=root4;
    	


    	System.out.println(new _606_Construct_String_from_Binary_Tree().tree2str(root));
    }
    public String tree2str(TreeNode t) {
        	
        StringBuilder sb = new StringBuilder();

        if(t!=null){
        	sb.append(t.val);

	        r(t,t.left,sb);

			r(t,t.right,sb);
        }

        return sb.toString();
    }
    private void r(TreeNode parent,TreeNode current,StringBuilder sb){
    	if(current == null){
    		return;
    	}
    	if(parent.left == null){
    		if(parent.right == null){
    			return;
    		}else{
    			sb.append("()");
    		}
    	}
    	sb.append("(");
    	sb.append(current.val);
        r(current,current.left,sb);
		r(current,current.right,sb);
    	sb.append(")");
    }
}