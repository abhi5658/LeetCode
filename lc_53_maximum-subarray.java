class Solution {
  public int maxSubArray(int[] nums) {
    int maxSum = nums[0];
    int sum = 0;
    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];
      // maxSum = Math.max(maxSum, sum);
      if (sum > maxSum) {
        maxSum = sum;
      }

      // if sum till this element is not positive ignore
      if (sum < 0) {
        sum = 0;
      }
    }
    return maxSum;
  }
}