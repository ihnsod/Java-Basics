package com.ihnsod.basics.leetcode.tree;

/**
 * @author Ihnsod
 * @create 2018/09/18
 **/
public class SameTree {
    /**
     * 给定两个二叉树，编写一个函数来检查它们是否相同。
     *
     * 如果两个二叉树在结构上相同并且节点具有相同的值，则认为它们是相同的。
     *
     * 例1：
     *
     * 输入：      1 1
     *           / \ / \
     *          2 3 2 3
     *
     *         [1,2,3]，[1,2,3]
     *
     * 输出： true
     * 例2：
     *
     * 输入：      1 1
     *           / \
     *          2 2
     *
     *         [1,2]，[1，null，2]
     *
     * 输出： false
     * 例3：
     *
     * 输入：      1 1
     *           / \ / \
     *          2 1 1 2
     *
     *         [1,2,1]，[1,1,2]
     *
     * 输出： false
     */

    public static boolean isSameTree(TreeNode source, TreeNode target) {
        //如果root为null说明此节点为叶子节点
        if (source == null && target == null) {
            return true;
        }
        if (source == null || target == null) {
            return false;
        }
        if (source.val != target.val) {
            return false;
        }
        return isSameTree(source.left, target.left) && isSameTree(source.right, target.right);
    }

    public static void main(String[] args) {
        TreeNode source = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        source.left = node1;
        source.right = node2;

        TreeNode target = new TreeNode(1);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        target.left = node3;
        target.right = node4;

        System.err.println(isSameTree(source, target));

    }
}
