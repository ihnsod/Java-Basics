package com.ihnsod.basics.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Ihnsod
 * @create 2018/09/18
 **/
public class SymmetricTree {
    /**
     * 给定二叉树，检查它是否是自身的镜像（即，围绕其中心对称）。
     *
     * 例如，这个二叉树[1,2,2,3,4,4,3]是对称的：
     *
     *      1
     *    /  \
     *   2    2
     *  / \  / \
     * 3  4 4   3
     * 但以下[1,2,2,null,3,null,3] 不是：
     *     1
     *    / \
     *   2 2
     *    \
     *    3 3
     */

    /**
     * 递归
     *
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }

    public static boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }

    /**
     * 非递归
     */

    private static boolean isSymmetricOne(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (root.left == null && root.right == null) {
            return true;
        }
        if (root.left == null || root.right == null) {
            return false;
        }
        LinkedList<TreeNode> left = new LinkedList<>();
        LinkedList<TreeNode> right = new LinkedList<>();
        left.add(root.left);
        right.add(root.right);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode n1 = left.poll();
            TreeNode n2 = right.poll();

            if (n1.val != n2.val) {
                return false;
            }
            if (n1.left == null && n2.right != null || n1.left != null && n2.left == null) {
                return false;
            }
            if (n1.right != null && n2.left == null || n1.right == null && n2.left != null) {
                return false;
            }
            if (n1.left != null && n2.right != null) {
                left.add(n1.left);
                right.add(n2.right);
            }
            if (n1.right != null && n2.left != null) {
                left.add(n1.right);
                right.add(n2.left);
            }
        }

        return true;
    }


    public static void main(String[] args) {
        boolean symmetricOne = isSymmetricOne(new TreeNode(1));
        System.err.println(symmetricOne);

        ArrayList<String> strings = new ArrayList<>();
    }
}
