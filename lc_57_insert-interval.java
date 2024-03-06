class Solution {
  // O(n) - all cases
  public int[][] insert(int[][] intervals, int[] newInterval) {
    ArrayList<int[]> output = new ArrayList<>();
    if (intervals.length == 0) {
      output.add(newInterval);
      return output.toArray(new int[output.size()][]);
    }
    if (newInterval[1] < intervals[0][0]) { // before the entire range
      output.add(newInterval);
      output.addAll(Arrays.asList(intervals));
      return output.toArray(new int[output.size()][]);
    }
    if (intervals[intervals.length - 1][1] < newInterval[0]) { // after the entire range
      output.addAll(Arrays.asList(intervals));
      output.add(newInterval);
      return output.toArray(new int[output.size()][]);
    }

    int i = 0;
    while (i < intervals.length && intervals[i][1] < newInterval[0]) {
      output.add(intervals[i]);
      i++;
    }
    int mergeStart = Math.min(newInterval[0], intervals[i][0]);
    while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
      i++;
    }
    int mergeEnd = Math.max(newInterval[1], intervals[i - 1][1]);
    output.add(new int[] { mergeStart, mergeEnd });
    while (i < intervals.length) {
      output.add(intervals[i]);
      i++;
    }
    return output.toArray(new int[output.size()][]);
  }
}

class Solution {
  // O(log(n)) for case where new interval covers everything
  // else average O(n) for adding the original intervals to output other than the
  // merged interval
  public int binarySearch(int[][] intervals, int time, char preference) {
    int left = 0;
    int right = intervals.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;

      int midIntervalStart = intervals[mid][0];
      int midIntervalEnd = intervals[mid][1];
      // int newIntervalStart = newInterval[0];
      // int newIntervalEnd = newInterval[1];

      if (midIntervalStart <= time && time <= midIntervalEnd) {
        // conflicting = true;
        return mid;
      }

      if (time < midIntervalStart) {
        right = mid - 1;
      } else { // newIntervalStart > midIntervalEnd (more than this full interval )
        left = mid + 1;
      }
    }

    if (right == -1)
      return right;
    if (left == intervals.length)
      return left;

    if (preference == 's') {
      return Math.max(left, right);
    } else { // prefernce == 'e'
      return Math.min(left, right);
    }
  }

  public int[][] insert(int[][] intervals, int[] newInterval) {
    int newIntervalStartIndex = binarySearch(intervals, newInterval[0], 's');
    int newIntervalEndIndex = binarySearch(intervals, newInterval[1], 'e');
    ArrayList<int[]> output = new ArrayList<>();
    int afterMergeIndex = intervals.length;
    for (int i = 0; i < newIntervalStartIndex; i++) {
      output.add(intervals[i]);
    }
    if (newIntervalStartIndex == -1) {
      if (newIntervalEndIndex == -1) {
        output.add(newInterval);
        afterMergeIndex = 0;
      } else if (newIntervalEndIndex < intervals.length) {
        // if(newInterval[1] >= intervals[newIntervalEndIndex][0]){
        output.add(new int[] { newInterval[0], Math.max(newInterval[1], intervals[newIntervalEndIndex][1]) });
        afterMergeIndex = newIntervalEndIndex + 1;
        // }
      } else { // new interval end out of bound
        output.add(newInterval);
      }
    } else if (newIntervalStartIndex < intervals.length) {
      if (newIntervalEndIndex < intervals.length) {
        output.add(new int[] {
            Math.min(newInterval[0], intervals[newIntervalStartIndex][0]),
            Math.max(newInterval[1], intervals[newIntervalEndIndex][1])
        });
        afterMergeIndex = newIntervalEndIndex + 1;
      } else {
        output.add(new int[] {
            Math.min(newInterval[0], intervals[newIntervalStartIndex][0]),
            newInterval[1]
        });
      }
    } else { // start of new interval outside of all ranges
      output.add(newInterval);
    }

    for (int i = afterMergeIndex; i < intervals.length; i++) {
      output.add(intervals[i]);
    }
    return output.toArray(new int[output.size()][]);
  }
}
