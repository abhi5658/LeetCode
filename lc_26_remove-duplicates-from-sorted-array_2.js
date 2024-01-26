/**
 * @param {number[]} nums
 * @return {number}
 */
var removeDuplicates = function (nums) {
  let replacerPointer = 0, currentNum = -101;
  const k = 0;
  for (const i = 0; i < nums.length; i++) {
    if (currentNum < nums[i]) {
      nums[replacerPointer] = nums[i];
      replacerPointer++;
      currentNum = nums[i];
      k++;
    }
  }
  return k;
};
