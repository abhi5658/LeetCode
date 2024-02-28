class Solution {

  // two options at an index: Max of
  // - use current and take current - 2
  // - skip current and take previous one
  //
  // 2,7,9,3,1
  // max till index
  // 0 [2] -> max(2) = 2 | dp=[2]
  // 1 [7] -> max(7,2) = 7 | dp=[2,7]
  // 2 [9] -> max(9+2,7) = 11 | dp=[2,7,11]
  // 3 [3] -> max(3+7,11) = 11 | dp=[2,7,11,11]
  // 4 [1] -> max(1+11,11) = 12 | dp=[2,7,11,11,12]
  public int rob(int[] nums) {
    int[] dp = new int[nums.length];
    if (nums.length == 1) {
      return nums[0];
    }
    dp[0] = nums[0];
    dp[1] = Math.max(nums[1], nums[0]);
    for (int i = 2; i < nums.length; i++) {
      // max of (current + current - 2) OR previous
      dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
    }
    return dp[nums.length - 1];
  }
}