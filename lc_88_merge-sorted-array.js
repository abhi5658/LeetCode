/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function (nums1, m, nums2, n) {
  let pos = m + n;
  for (let i = pos; i > 0; i--) {
    if (m > 0 && n > 0) {
      // if(n>0){
      if (nums1[m - 1] > nums2[n - 1]) {
        nums1[pos - 1] = nums1[m - 1]
        m--;
      } else {
        nums1[pos - 1] = nums2[n - 1]
        n--;
      }
      console.log('writing nums1', nums1);
      // }
    } else if (n > 0) {
      console.log('writing else nums1', nums1);
      nums1[pos - 1] = nums2[n - 1]
      n--;
    }
    pos--;
  }
  console.log('nums1', nums1);
};
merge([1, 2, 3, 0, 0, 0], 3, [2, 5, 6], 3);
merge([1], 1, [], 0);
merge([0], 0, [1], 1);
