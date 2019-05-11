package com.ihnsod.basics.leetcode.tree;

/**
 * @author Ihnsod
 * @create 2018/09/18
 **/
public class BalancedBinaryTree {
    /**
     * 给定二叉树，确定它是否是高度平衡的。
     *
     * 对于此问题，高度平衡二叉树定义为：
     *
     * 一个二叉树，其中每个节点的两个子树的深度从不相差超过1。
     *
     * 例1：
     *
     * 鉴于以下树[3,9,20,null,null,15,7]：
     *
     *     3
     *    / \
     *   9 20
     *     / \
     *    15 7
     * 回归true。
     *
     * 例2：
     *
     * 鉴于以下树[1,2,2,3,3,null,null,4,4]：
     *
     *        1
     *       / \
     *      2 2
     *     / \
     *    3 3
     *   / \
     *  4 4
     * 返回false。
     */


    public static boolean isBalance(TreeNode root) {
        return hepler(root) >= 0;
    }

    public static int hepler(TreeNode root) {
        //如果此节点为叶子节点则返回为平衡二叉树
        if (root == null) {
            return 0;
        }
        //递归获取该节点左子树的判断值
        int left = hepler(root.left);
        //递归获取该节点右子树的判断值
        int right = hepler(root.right);
        //如果左子树或者右子树其中有一个不为平衡二叉树则 此树为非平衡二叉树
        if (left < 0 || right < 0) {
            return -1;
        }
        //如果左子树和右子树的深度相差大于1 则是非平衡二叉树
        if (Math.abs(left - right) > 1) {
            return -1;
        }
        //返回此层级的树的结果中最深的结果加上层级的深度加1
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);
        TreeNode treeNode6 = new TreeNode(7);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
//        treeNode2.left = treeNode5;
//        treeNode2.left = treeNode6;

        System.err.println(isBalance(treeNode));

    }

}
