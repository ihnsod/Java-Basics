package com.ihnsod.basics.leetcode.tree;

import java.util.LinkedList;

/**
 * @author Ihnsod
 * @create 2018/09/18
 **/
public class BinaryTreeMaxDepth {

    /**
     * 给定二叉树，找到它的最大深度。
     *
     * 最大深度是从根节点到最远叶节点的最长路径上的节点数。
     *
     * 注意：  叶子是没有子节点的节点。
     *
     * 例：
     *
     * 给定二叉树[3,9,20,null,null,15,7]，
     *
     *     3
     *    / \
     *   9 20
     *     / \
     *    15 7
     * 返回其深度= 3。
     */


    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * 层序遍历的方式
     *
     * @param root
     * @return
     */
    public static int maxDepthOne(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        //当前队列中的元素数量
        int curNum = 1;
        //需要增加的元素数量
        int nextNum = 0;
        while (!queue.isEmpty()) {
            //poll 为栈的操作形式 弹出最上面的一个元素
            TreeNode poll = queue.poll();
            curNum--;
            //如果当前节点的左子树不为null 增加此节点到栈中
            if (poll.left != null) {
                queue.add(poll.left);
                nextNum++;
            }
            //如果当前节点的右子树不为null 增加此节点到栈中
            if (poll.right != null) {
                queue.add(poll.right);
                nextNum++;
            }
            //如果此层级的节点已经处理完 把将要处理的下级节点的数量设置为nextNum
            if (curNum == 0) {
                curNum = nextNum;
                //处理完此层 把nextNum置0 树的深度加1
                nextNum = 0;
                level++;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(2);
        TreeNode treeNode2 = new TreeNode(3);
        TreeNode treeNode3 = new TreeNode(4);
        TreeNode treeNode4 = new TreeNode(5);
        TreeNode treeNode5 = new TreeNode(6);

        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode4.left = treeNode5;

        int j = maxDepth(treeNode);

        int k = maxDepthOne(treeNode);

    }
}
