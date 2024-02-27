class Solution {
  List<List<Integer>> ans = new ArrayList<>();

  // startPointer = 0; candidates = [2,3,6,7]; base = []; currentTotal = 0;
  public void createCombination(int startPointer, int[] candidates, List<Integer> base, int currentTotal,
      int target) {
    if (currentTotal == target) {
      // List<Integer> copy = new ArrayList<>();
      // copy.addAll(base);
      // ans.add(copy);
      ans.add(new ArrayList<>(base));
      return;
    }
    if (currentTotal > target) {
      return;
    }
    if (startPointer > candidates.length - 1) {
      return;
    }

    base.add(candidates[startPointer]);
    // startPointer = 0; candidates = [2,3,6,7]; base = [2]; currentTotal = 2;
    createCombination(startPointer, candidates, base, currentTotal + candidates[startPointer], target);

    // as base is passed by reference
    // this step helps clean the added element at all steps before moving on to
    // other side of tree
    base.remove(base.size() - 1);
    // startPointer = 1; candidates = [3,6,7]; base = []; currentTotal = 0;
    createCombination(startPointer + 1, candidates, base, currentTotal, target);
    return;
  }

  public List<List<Integer>> combinationSum(int[] candidates, int target) {
    createCombination(0, candidates, new ArrayList<Integer>(), 0, target);
    return ans;
  }
}