class Solution {
  public int[] twoSum(int[] nums, int target) {
    int size = nums.length;
    int indexOne = -1, indexTwo = -1;

    for (int i = 0; i < size; i++) {
      int firstElement = nums[i];
      for (int j = i + 1; j < size; j++) {
        int secondElement = nums[j];
        if (firstElement + secondElement == target) {
          indexOne = i;
          indexTwo = j;
          break;
        }
      }
      if (indexOne > -1 && indexTwo > -1) {
        break;
      }
    }
    return new int[] { indexOne, indexTwo };
  }
}