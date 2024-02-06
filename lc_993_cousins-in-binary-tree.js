// https://leetcode.com/problems/cousins-in-binary-tree/
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
 * @param {number} x
 * @param {number} y
 * @return {boolean}
 */
var isCousins = function (root, x, y) {
    /**
     * BFS
     * Use BFS to locate the depths of x and y;
     * During the BFS iterations, check if x and y share same parent, if yes, return false;
     * after iterations, check if x and y are in the same depth.
     */
    const q = [root];
    while (q.length) {
        // need to save size as q.length will change after q.shift
        const size = q.length;
        let xExist = false;
        let yExist = false;
        for (let i = 0; i < size; i++) {
            const node = q.shift();
            if (node.val === x) xExist = true;
            if (node.val === y) yExist = true;
            if (node.left && node.right) {
                if (node.left.val === x && node.right.val === y) {
                    return false;
                }
                if (node.left.val === y && node.right.val === x) {
                    return false;
                }
            }
            if (node.left) q.push(node.left);
            if (node.right) q.push(node.right);
        }
        if (xExist && yExist) return true;
    }
    return false;
};
var isCousins2 = function (root, x, y) {
    /**
     * DFS
     * Use DFS to find each node's parent and level,
     * and put them into a HashMap, then check if x and y are cousins.
     */
    const res = []; // store [parent, level]
    const dfs = (node, parent, level) => {
        // if both already found
        if (res.length === 2) return;

        if (!node) return null;
        if (node.val === x || node.val === y) {
            res.push([parent, level]);
        }
        dfs(node.left, node, level + 1);
        dfs(node.right, node, level + 1);
    }
    dfs(root, null, 0);
    return res[0][0] != res[1][0] && res[0][1] === res[1][1];
};
