package com.ihnsod.basics.leetcode.tree;

import java.util.LinkedList;

/**
 * @author Ihnsod
 * @create 2018/09/18
 **/
public class BinaryTreeMinDepth {

    /**
     * 给定二叉树，找到它的最小深度。
     *
     * 最小深度是沿从根节点到最近的叶节点的最短路径上的节点数。
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
     * 返回其最小深度= 2。
     */

    /**
     * 递归的方式  由于求的是最小深度所以必须增加一个叶子节点的判断
     *
     * @param root
     * @return
     */
    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return minDepth(root.left) + 1;
        }
        if (root.right == null) {
            return minDepth(root.right) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right));
    }

    /**
     * 非递归层序遍历的方式
     *
     * @param root
     * @return
     */

    public static int minDepthOne(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> queue = new LinkedList();
        int curNum = 0;
        int lastNum = 1;
        int level = 1;
        //offer方法和add方法操作没有任何区别 只不过 offer 是栈的操作形式
        queue.offer(root);
        while (!queue.isEmpty()) {
            //poll()返回此列表的头元素
            TreeNode cur = queue.poll();
            //如果当前节点的左右子数均为null,说明此节点为叶子节点 直接返回level(深度)
            if (cur.left == null && cur.right == null) {
                return level;
            }
            //列表中的元素个数减一
            lastNum--;
            if (cur.left != null) {
                queue.offer(cur.left);
                curNum++;
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                curNum++;
            }
            //如果此层级的节点已经处理完并没有叶子节点 把将要处理的下级节点的数量设置为curNum
            if (lastNum == 0) {
                lastNum = curNum;
                //把curNum 置0 树的深度加1
                curNum = 0;
                level++;
            }
        }
        return 0;
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

        System.err.println(minDepth(treeNode));
        System.err.println(minDepthOne(treeNode));

    }

}
