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

class Node {
  String value;
  int timestamp;

  public Node(String xvalue, int xtimestamp) {
    value = xvalue;
    timestamp = xtimestamp;
  }
}

class TimeMap {
  Map<String, ArrayList<Node>> keys;
  // foo->[[1,"bar"],[4,"bar2"]];->[[4,"bar2"],[1,"bar"]]
  // O(nlog10^5) O(1)

  public TimeMap() {
    keys = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) { //
    ArrayList<Node> list = keys.get(key);
    if (list == null) {
      list = new ArrayList<Node>();
      keys.put(key, list);
    }
    Node node = new Node(value, timestamp);
    list.add(node);
  }

  public String get(String key, int timestamp) { // O(1 + logn)
    ArrayList<Node> list = keys.get(key);
    if (list == null) {
      return "";
    }
    if (timestamp > list.get(list.size() - 1).timestamp) {
      return list.get(list.size() - 1).value;
    }
    if (timestamp < list.get(0).timestamp) {
      return "";
    }

    int left = 0;
    int right = list.size() - 1;
    int mid = -1;

    while (left < right) {
      mid = left + (right - left) / 2;
      int midTimestamp = list.get(mid).timestamp;

      if (midTimestamp == timestamp) {
        return list.get(mid).value;
      } else if (midTimestamp > timestamp) {
        right = mid - 1;
      } else { // midTimestamp < timestamp
        left = mid;
      }
    }

    if (list.get(right).timestamp <= timestamp) {
      return list.get(right).value;
    }

    if (list.get(left).timestamp <= timestamp) {
      return list.get(left).value;
    }

    return "";
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

  public static void main(String[] args) {
    System.out.println("hey");
    String[] input = new String[] { "set", "get", "get", "set", "get", "get", "set", "set", "set", "set", "set",
        "get" };
    String[][] payload = { { "foo", "bar", "1" }, { "foo", "1" }, { "foo", "3" }, { "foo", "bar2", "4" },
        { "foo", "4" },
        { "foo", "5" }, { "foo", "zigzag", "7" }, { "foo", "conundrum", "8" },
        { "foo", "hyperbole", "9" }, { "foo", "silhouette", "10" }, { "foo", "blasphemy", "11" }, { "foo", "9" } };

    TimeMap obj = new TimeMap();
    String[] output = new String[input.length];
    for (int i = 0; i < input.length; i++) {
      String[] data = payload[i];
      if (input[i] == "set") {
        obj.set(data[0], data[1], Integer.parseInt(data[2]));
      } else {
        output[i] = obj.get(data[0], Integer.parseInt(data[1]));
      }
    }
    System.out.println(Arrays.toString((output)));
    // null,"bar","bar",null,"bar2","bar2",null,null,null,null,null,"hyperbole"

    Solution solution = new Solution();
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
