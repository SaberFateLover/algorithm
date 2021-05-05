package kmp;

import java.awt.image.AreaAveragingScaleFilter;
import java.lang.invoke.MutableCallSite;
import java.sql.SQLOutput;
import java.util.*;

/**
 * @author saber
 * @date 2021-05-03 10:43
 * 记录作者的leetcode刷题记录，草稿
 */
public class Solution {




    public static void main(String[] args) {
//        int[]  a =new int[]{3,2,1,6,0,5};
//        int[]  b =new int[2];

        constructMaximumBinaryTree(null);
//        System.arraycopy( a, 4, b, 0, b.length);
//        for (int v : b){
//            System.out.println(v);
//        }


    }


    /**
     * 夏日炎炎，小男孩 Tony 想买一些雪糕消消暑。
     * 商店中新到 n 支雪糕，用长度为 n 的数组 costs 表示雪糕的定价，其中 costs[i] 表示第 i 支雪糕的现金价格。Tony 一共有 coins 现金可以用于消费，他想要买尽可能多的雪糕。
     * 给你价格数组 costs 和现金量 coins ，请你计算并返回 Tony 用 coins 现金能够买到的雪糕的 最大数量 。
     * 注意：Tony 可以按任意顺序购买雪糕。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-ice-cream-bars
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * 评论区感觉好玩的解发，学习一下，下面作者题解链接，自己的题解很简单就不写出来了。
     * https://leetcode-cn.com/problems/maximum-ice-cream-bars/solution/javadui-26msshuang-bai-by-jesing-193k/
     * @param costs
     * @param coins
     * @return
     */
    public int maxIceCream(int[] costs, int coins) {
        //用大堆顶
        PriorityQueue<Integer> queue =new PriorityQueue<>((o1,o2)->o2-o1);

        int sum = 0;//当前堆中的和

        for (Integer a:costs) {
            int val = a+sum;
            if(val <= coins){
                sum+=a;
                queue.offer(a);
            }else if(!queue.isEmpty() && queue.peek()>a){
                sum-=queue.poll();
                queue.add(a);
                sum+=a;
            }
        }
        return queue.size();
    }

    public  static TreeNode constructMaximumBinaryTree(int[] nums) {
        if(nums ==null ){
            return  null;
        }
        return  forEachNums(nums);
    }

    /**
     * 给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：
     *
     * 二叉树的根是数组 nums 中的最大元素。
     * 左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
     * 右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
     * 返回有给定数组 nums 构建的 最大二叉树 。
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/maximum-binary-tree
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    private  static TreeNode forEachNums(int[] nums) {
        if(nums.length ==0){
            return  null;
        }
        if(nums.length==1){
            return  new TreeNode(nums[0]);
        }
       int index = getMaxIndex(nums);
       TreeNode  currentMaxNode =new TreeNode(nums[index]);

       //这个是左边
      TreeNode leftSon= forEachNums(getSubArr(nums,index,true));
       currentMaxNode.left=leftSon;
        //这个是右边
        TreeNode rightSon= forEachNums(getSubArr(nums,index,false));
        currentMaxNode.right=rightSon;
      return  currentMaxNode;
    }

    private static   int getMaxIndex(int[] nums) {
        int max =-1;
        int index =0;
        for (int i = 0; i < nums.length; i++) {
             if(nums[i]>max){
                 max=nums[i];
                 index = i;
             }
        }
        return  index;
    }

    private static int[] getSubArr(int[] nums,int maxIndex,boolean left) {
        int[] arr;
        //左边
        if(left){
            arr =new int[maxIndex];
            if (maxIndex >= 0) System.arraycopy(nums, 0, arr, 0, maxIndex);
        //右边
        }else {
            arr =new int[nums.length-maxIndex-1];
            if (arr.length >= 0) System.arraycopy(nums, maxIndex+1, arr, 0, arr.length);
        }
        return  arr;
    }
}


class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
       this.val = val;
       this.left = left;
       this.right = right;
    }
 }
