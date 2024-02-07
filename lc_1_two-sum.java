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

class Solution {
  public int[] twoSum(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if (nums[i] + nums[j] == target) {
          return new int[] { i, j };
        }
      }
    }
    return new int[] { 0, 0 };
  }
}

class Solution { // O(n)
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> required = new HashMap<>();
    required.put(nums[0], 0);
    for (int i = 1; i < nums.length; i++) {
      int currentNum = nums[i];
      Integer findRequired = required.get(target - currentNum);
      if (findRequired != null) {
        return new int[] { findRequired.intValue(), i };
      } else {
        required.put(nums[i], i);
      }
    }
    return new int[] { 0, 0 };
  }
}
