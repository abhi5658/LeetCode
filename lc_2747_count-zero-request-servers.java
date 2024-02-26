class Solution { // simple, elegant, so beatiful, so elegant, just looking like a wow
  public int[] countServers(int n, int[][] logs, int x, int[] queries) {
    Arrays.sort(logs, (a, b) -> a[1] - b[1]);
    int[][] queryIndex = new int[queries.length][2];
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      queryIndex[i] = new int[] { queries[i], i };
    }
    Arrays.sort(queryIndex, (a, b) -> a[0] - b[0]);

    // stores server count in window where index is server id
    int[] serverCountInWindow = new int[n + 1]; // servers [0(not used), 1, 2 ..... n]
    int uniqueServers = 0;

    int logStart = 0;
    int logEnd = 0;

    for (int i = 0; i < queryIndex.length; i++) {
      int queryStart = queryIndex[i][0] - x;
      int queryEnd = queryIndex[i][0];

      // add window elements
      while (logEnd < logs.length && logs[logEnd][1] <= queryEnd) {
        serverCountInWindow[logs[logEnd][0]]++;
        if (serverCountInWindow[logs[logEnd][0]] == 1) // if count was 0 earlier then this is a new server in window
          uniqueServers++;
        logEnd++;
      }
      // remove out of window elements
      while (logStart < logs.length && logs[logStart][1] < queryStart) {
        serverCountInWindow[logs[logStart][0]]--;
        if (serverCountInWindow[logs[logStart][0]] == 0)
          uniqueServers--;
        logStart++;
      }
      ans[queryIndex[i][1]] = n - uniqueServers;
    }
    return ans;
  }

}

class Solution { // removed window hashmap
  public int[] countServers(int n, int[][] logs, int x, int[] queries) {
    Arrays.sort(logs, (a, b) -> a[1] - b[1]);
    int[][] queryIndex = new int[queries.length][2];
    int[] ans = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      queryIndex[i] = new int[] { queries[i], i };
    }
    Arrays.sort(queryIndex, (a, b) -> a[0] - b[0]);

    int[] window = new int[n + 1];
    int uniqueServers = 0;

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
        ans[queryIndex[queryIndexPointer][1]] = n - uniqueServers;
        queryIndexPointer++;
        if (queryIndexPointer == queryIndex.length) {
          break;
        }
        queryStart = queryIndex[queryIndexPointer][0] - x;
        queryEnd = queryIndex[queryIndexPointer][0];

        // next query lying in previous window
        if (queryStart <= windowEnd - 1 && windowEnd - 1 < queryEnd) {
          continue;
        }

        // far away new window
        if (queryStart > windowEnd - 1) {
          windowStart = queryStart;
          windowEnd = queryStart;
          logStart = logEnd;
          window = new int[n + 1];
          uniqueServers = 0;
          continue;
        }
        // reset
      }

      // find logs of windowEnd and create set of unique servers at windowEnd
      // windowEnd is new window element
      while (windowEnd < queryEnd + 1) {
        if (logEnd == logs.length) { // no more logs remain to process
          windowEnd = queryEnd + 1;
          continue;
        }
        int[] log = logs[logEnd];
        int server = log[0];
        int time = log[1];
        if (time < windowStart) {
          // happens when window start is far and logs are of older time - skip the logs
          logStart++;
          logEnd++;
        } else if (time == windowEnd) { // found the log - add to count
          // window.put(server, window.getOrDefault(server, 0) + 1);
          window[server]++;
          if (window[server] == 1)
            uniqueServers++;
          logEnd++;
        } else if (time > windowEnd) {
          windowEnd++;
        }
      }

      // remove out of window element at start
      while (windowStart < queryStart) {
        if (logStart == logs.length) {
          windowStart = queryStart;
          continue;
        }
        int[] log = logs[logStart];
        int server = log[0];
        int time = log[1];

        if (time == windowStart) {
          window[server]--;
          if (window[server] == 0)
            uniqueServers--;
          logStart++;
        } else {
          windowStart++;
        }
      }

    } // end of topmost while loop

    return ans;
  }

}

class Solution { // removed time vs server set hashmap
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

        // next query lying in previous window
        if (queryStart <= windowEnd - 1 && windowEnd - 1 < queryEnd) {
          continue;
        }

        // far away new window
        if (queryStart > windowEnd - 1) {
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
        if (logEnd == logs.length) { // no more logs remain to process
          // windowEnd++;
          windowEnd = queryEnd + 1;
          continue;
        }
        int[] log = logs[logEnd];
        int server = log[0];
        int time = log[1];
        if (time < windowStart) {
          // happens when window start is far and logs are of older time - skip the logs
          logStart++;
          logEnd++;
        } else if (time == windowEnd) { // found the log - add to count
          window.put(server, window.getOrDefault(server, 0) + 1);
          logEnd++;
        } else if (time > windowEnd) {
          windowEnd++;
        }
      }

      // remove out of window element at start
      while (windowStart < queryStart) {
        if (logStart == logs.length) {
          // windowStart++;
          windowStart = queryStart;
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
    Map<Integer, Set<Integer>> timeServerSet = new HashMap<>();

    int logsPointer = 0;
    int queryIndexPointer = 0;
    int queryStart = queryIndex[queryIndexPointer][0] - x;
    int queryEnd = queryIndex[queryIndexPointer][0];
    int windowStart = queryStart;
    int windowEnd = queryStart;

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
          continue;
        }
        // reset
      }

      // find logs of windowEnd and create set of unique servers at windowEnd
      // windowEnd is new window element
      while (logsPointer < logs.length) {
        int[] log = logs[logsPointer];
        if (log[1] < windowEnd) {
          logsPointer++;
          continue; // this while loop
        } else if (log[1] == windowEnd) {
          Set<Integer> currTimeSet = timeServerSet.get(windowEnd);
          if (currTimeSet == null) {
            currTimeSet = new HashSet<Integer>();
            timeServerSet.put(windowEnd, currTimeSet);
          }
          currTimeSet.add(log[0]);
          logsPointer++;
        } else {
          break; // this while loop
        }
      }

      // add new element
      for (int server : timeServerSet.getOrDefault(windowEnd, new HashSet<Integer>())) {
        window.put(server, window.getOrDefault(server, 0) + 1);
      }
      windowEnd++;

      // remove out of window element at start
      while (windowStart < queryStart) {
        for (int server : timeServerSet.getOrDefault(windowStart, new HashSet<Integer>())) {
          Integer serverCount = window.get(server);
          if (serverCount != null) {
            if (serverCount.intValue() == 1) {
              window.remove(server);
            } else {
              window.put(server, serverCount.intValue() - 1);
            }
          }
        }
        windowStart++;
      }
    } // end of topmost while loop

    return ans;
  }
}