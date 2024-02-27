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
    int ansIndex = -1;

    while (left <= right) {
      mid = left + (right - left) / 2;
      int midTimestamp = list.get(mid).timestamp;

      if (midTimestamp == timestamp) {
        ansIndex = mid;
        break;
      } else if (midTimestamp <= timestamp) { // = is repeat; can be removed
        ansIndex = mid; // possible ans because matching condition
        left = mid + 1;
      } else { // midTimestamp > timestamp
        right = mid - 1;
      }
    }

    return list.get(ansIndex).value;
    // return ""; // this case already handled above
  }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */