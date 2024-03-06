import java.util.*;

/**
 * vscode: Add the folder to Java Source path before running any file
 * - Right click on folder and select "Add Folder to Java Source Path"
 */

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public int[] countServers(int n, int[][] logs, int x, int[] queries) {
    Arrays.sort(logs, (a, b) -> a[1] - b[1]);
    int[][] queryIndex = new int[queries.length][2];
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      queryIndex[i] = new int[] { queries[i], i };
    }
    Arrays.sort(queryIndex, (a, b) -> a[0] - b[0]);

    Map<Integer, Integer> window = new HashMap<>();

    int queryIndexPointer = 0;
    int queryStart = queryIndex[queryIndexPointer][0] - x;
    int queryEnd = queryIndex[queryIndexPointer][0];
    int windowStart = queryStart;
    int windowEnd = queryStart;
    int logStart = 0;
    int logEnd = 0;

    while (queryIndexPointer < queryIndex.length) {
      boolean initial = (windowStart == windowEnd);

      if (!initial && windowEnd == queryEnd + 1) { // window achieved
        ans[queryIndex[queryIndexPointer][1]] = n - window.size();
        queryIndexPointer++;
        if (queryIndexPointer == queryIndex.length) {
          break;
        }
        queryStart = queryIndex[queryIndexPointer][0] - x;
        queryEnd = queryIndex[queryIndexPointer][0];
        if (queryStart <= windowEnd - 1 && windowEnd - 1 < queryEnd) {
          continue;
        }
        if (queryStart > windowEnd - 1) { // far away new window
          windowStart = queryStart;
          windowEnd = queryStart;
          window = new HashMap<>();
          logStart = logEnd;
          continue;
        }
        // reset
      }

      // find logs of windowEnd and create set of unique servers at windowEnd
      // windowEnd is new window element
      while (windowEnd < queryEnd + 1) {
        if (logEnd == logs.length) {
          windowEnd++;
          continue;
        }
        int[] log = logs[logEnd];
        int server = log[0];
        int time = log[1];
        if (time < windowStart) {
          logStart++;
          logEnd++;
        } else if (time == windowEnd) {
          window.put(server, window.getOrDefault(server, 0) + 1);
          logEnd++;
        } else if (time > windowEnd) {
          windowEnd++;
        }
      }

      // remove out of window element at start
      while (windowStart < queryStart) {
        if (logStart == logs.length) {
          windowStart++;
          continue;
        }
        int[] log = logs[logStart];
        int server = log[0];
        int time = log[1];

        if (time == windowStart) {
          Integer serverCount = window.get(server);
          if (serverCount != null) {
            if (serverCount.intValue() == 1) {
              window.remove(server);
            } else {
              window.put(server, serverCount.intValue() - 1);
            }
          }
          logStart++;
        } else {
          windowStart++;
        }
      }

    } // end of topmost while loop

    return ans;
  }

  public static void printList(ListNode list) {
    ListNode currNode = list;

    System.out.print("LinkedList: ");

    // Traverse through the LinkedList
    while (currNode != null) {
      // Print the data at current node
      System.out.print(currNode.val + " ");

      // Go to next node
      currNode = currNode.next;
    }
  }

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

  public static void main(String[] args) {
    System.out.println("hey");
    // int[][] intervals = { { 1, 3 }, { 6, 9 } };
    // int[] newInterval = { 2, 5 };
    // int[][] intervals = { { 1, 2 }, { 3, 5 }, { 6, 7 }, { 8, 10 }, { 12, 16 } };
    // int[] newInterval = { 4, 8 };
    int[][] intervals = { { 1, 5 } };
    int[] newInterval = { 2, 7 };
    Solution solution = new Solution();
    int[][] output = solution.insert(intervals, newInterval);
    System.out.println(Arrays.deepToString((output)));
    // null,"bar","bar",null,"bar2","bar2",null,null,null,null,null,"hyperbole"

    // System.out.println("aa: " + solution.isAnagram("abca", "aabc"));
    // int[]] input = new int[] { 1, 1, 1, 2, 2, 3 };

    // int n = 4;

    // int[][] logs = new int[][] { { 4, 3 }, { 2, 16 }, { 1, 21 }, { 3, 22 }, { 1,
    // 13 }, { 3, 10 }, { 2, 1 }, { 1, 12 },
    // { 4, 13 }, { 2, 18 } };
    // int x = 8;
    // int[] queries = new int[] { 14, 28, 29 };
    // int n = 4;
    // int[][] logs = new int[][] { { 2, 30 }, { 2, 5 }, { 3, 9 }, { 4, 21 } };
    // int x = 9;
    // int[] queries = new int[] { 11, 28, 16, 18 };
    // int n = 3;
    // int[][] logs = new int[][] { { 1, 3 }, { 2, 6 }, { 1, 5 } };
    // int x = 5;
    // int[] queries = new int[] { 10, 11 };
    // int n = 3;
    // int[][] logs = new int[][] { { 2, 4 }, { 2, 1 }, { 1, 2 }, { 3, 1 } };
    // int x = 2;
    // int[] queries = new int[] { 3, 4 };
    // int[] ans = solution.countServers(n, logs, x, queries);
    // System.out.println(Arrays.toString(ans));
    // int ans = solution.findMin(new int[] { 3, 4, 5, 1, 2 });
    // int[] aa = solution.twoSum(new int[] { 2, 7, 11, 15 }, 9);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 2, 4 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 3 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    // System.out.println("output: " + solution.isValid("()"));
    // System.out.println("output: " + solution.isValid("()[]{}"));
    // System.out.println("output: " + solution.isValid("(]"));
    // System.out.println("output: " + solution.isValid("(("));
    // System.out.println("output: " + solution.isValid("){"));
  }
}
