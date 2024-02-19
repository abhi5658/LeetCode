/**
 * Definition of Interval:
 * public class Interval {
 * public int start, end;
 * public Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 * }
 */

class Solution {
  public boolean canAttendMeetings(List<Interval> intervals) {
    // Interval[] arr = intervals.toArray(new Interval[0]);
    // Arrays.sort(arr,(a,b)->a.start - b.start);
    Collections.sort(intervals, (a, b) -> a.start - b.start);
    for (int i = 0; i < intervals.size() - 1; i++) {
      if (intervals.get(i).end > intervals.get(i + 1).start) {
        return false;
      }
    }
    return true;
  }
}

/**
 * Definition of Interval:
 * public class Interval {
 * public int start, end;
 * public Interval(int start, int end) {
 * this.start = start;
 * this.end = end;
 * }
 * }
 */

class Solution {
  public boolean canAttendMeetings(List<Interval> intervals) {
    Interval[] arr = intervals.toArray(new Interval[0]);
    Arrays.sort(arr, (a, b) -> a.start - b.start);
    for (int i = 0; i < arr.length - 1; i++) {
      if (arr[i].end > arr[i + 1].start) {
        return false;
      }
    }
    return true;
  }
}
