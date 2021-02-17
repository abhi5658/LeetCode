// https://leetcode.com/problems/symmetric-tree/
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {TreeNode} root
 * @return {boolean}
 */
var isSymmetric = function (root) {
  if (!root) return true;
  // if(!root.left.val || !root.right.val) return false;
  // if(root.left.val !== root.right.val) return false;
  return isMirror(root.left, root.right);
};
var isMirror = (t1, t2) => {
  if (!t1 && !t2) return true;
  if (!t1 || !t2) return false;
  if (t1.val !== t2.val) return false;
  return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
}
