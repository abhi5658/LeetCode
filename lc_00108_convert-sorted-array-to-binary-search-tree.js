// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.left = (left===undefined ? null : left)
 *     this.right = (right===undefined ? null : right)
 * }
 */
/**
 * @param {number[]} nums
 * @return {TreeNode}
 */
var sortedArrayToBST = function (nums) {
    const len = nums.length;
    if (len === 0) return null;
    const mid = Math.floor(len / 2);
    // 100 ms - 43.2 MB
    return new TreeNode(
        nums[mid],
        sortedArrayToBST(nums.slice(0, mid)),
        sortedArrayToBST(nums.slice(mid + 1, len))
    );
    // 96 ms - 43.5 MB
    // const root = { val: nums[mid] };
    // root.left = sortedArrayToBST(nums.slice(0, mid));
    // root.right = sortedArrayToBST(nums.slice(mid + 1, len));
    // return root;
};
function TreeNode(val, left, right) {
    this.val = (val === undefined ? 0 : val)
    this.left = (left === undefined ? null : left)
    this.right = (right === undefined ? null : right)
}
