/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
  public boolean isSubtree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null)
      return true;
    if (root == null || subRoot == null)
      return false;

    boolean intermediateSubTree = false;
    if (root.val == subRoot.val) {// there is a chance this can be same
      intermediateSubTree = isSametree(root.left, subRoot.left) && isSametree(root.right, subRoot.right);
    }

    boolean furtherSubTree = intermediateSubTree
        || isSubtree(root.left, subRoot)
        || isSubtree(root.right, subRoot); // whichever is true first

    return intermediateSubTree || furtherSubTree;
  }

  public boolean isSametree(TreeNode root, TreeNode subRoot) {
    if (root == null && subRoot == null)
      return true;
    if (root == null || subRoot == null)
      return false;
    return ((root.val == subRoot.val)
        && isSametree(root.left, subRoot.left)
        && isSametree(root.right, subRoot.right));
  }
}
