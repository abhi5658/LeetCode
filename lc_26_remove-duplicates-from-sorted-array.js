/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
    if (nums.length === 0) {
        return 0;
    }
    let startIndex = 0;
    for (let i = 1; i < nums.length; i++) {
        // duplicate
        if (nums[i - 1] === nums[i]) {
            nums.splice(startIndex + 1, 1);
            i--;
        } else {
            startIndex++;
        }
    }
    return (startIndex + 1);
};