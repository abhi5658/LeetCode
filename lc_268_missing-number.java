class Solution {
  public int missingNumber(int[] nums) {
    int total = 0;
    int numsLength = nums.length;
    for (int i = 0; i < numsLength; i++) {
      total += nums[i];
    }
    return ((numsLength * (numsLength + 1)) / 2) - total;
  }
}
