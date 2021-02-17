/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number}
 */
var search = function (nums, target) {
    return binarySearch(nums, 0, nums.length - 1, target);
};
const binarySearch = (arr, start, end, x) => {
    console.log('start', start, 'end', end);
    if (start > end) return -1;
    const mid = Math.floor((start + end) / 2);
    if (arr[mid] === x) {
        return mid;
    } else if (arr[mid] < x) {
        return binarySearch(arr, mid + 1, end, x)
    } else if (arr[mid] > x) {
        return binarySearch(arr, start, mid - 1, x)
    }
}
console.log(search([-1, 0, 3, 5, 9, 12], 9));
console.log(search([5], 5));
