class Solution {
  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
    int previousStartTime = intervals[0][0];
    int previousEndTime = intervals[0][1];
    ArrayList<int[]> output = new ArrayList<>();
    for (int i = 1; i < intervals.length; i++) {
      if (intervals[i][0] - previousEndTime > 0) {
        output.add(new int[] { previousStartTime, previousEndTime });
        previousStartTime = intervals[i][0];
        previousEndTime = intervals[i][1];
      } else {
        previousEndTime = Math.max(intervals[i][1], previousEndTime);
      }
    }
    output.add(new int[] { previousStartTime, previousEndTime });
    return output.toArray(new int[output.size()][]);
  }
}