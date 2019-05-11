package com.ihnsod.basics.leetcode.tree;

/**
 * @author: Ihnsod
 * @create: 2018/09/19 21:32
 **/

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉树的中序遍历
 */
public class BinaryTreeInorderTraversal {


    /**
     * 给定二叉树，返回其节点值的inorder遍历。
     *
     * 例：
     *
     * 输入： [1，null，2,3]
     *    1
     *     \
     *      2
     *     /
     *    3
     *
     * 产出： [1,3,2]
     * @param root
     * @return
     */


    /**
     * 递归方式
     *
     * @param root
     * @return
     */
    //注释

    public static ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        helper(root, result);
        return result;
    }

    private static void helper(TreeNode root, ArrayList<Integer> result) {
        if (root == null) {
            return;
        }
        helper(root.left, result);
        result.add(root.val);
        helper(root.right, result);
    }

    /**
     * 迭代方式
     */
    public static ArrayList<Integer> inorderTraversalOne(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                root = stack.pop();
                result.add(root.val);
                root = root.right;
            }
        }
        return result;
    }


    /**
     * 用常量空间来中序遍历一颗二叉树
     */
    private static ArrayList<Integer> inorderTraversalTwo(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.left == null) {
                res.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    pre.right = null;
                    res.add(cur.val);
                    cur = cur.right;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);

        treeNode.right = treeNode1;
        treeNode1.left = treeNode3;

        System.err.println(inorderTraversal(treeNode));
        System.err.println(inorderTraversalOne(treeNode));
        System.err.println(inorderTraversalTwo(treeNode));
    }

}
