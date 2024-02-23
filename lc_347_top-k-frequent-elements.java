class Solution {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
    }

    // we need index 0 as well
    // - because using index directly as frequency -> 0,1,2...n -> n+1 size
    List<Integer>[] frequencyList = new List[nums.length + 1];

    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      if (frequencyList[entry.getValue()] == null) {
        frequencyList[entry.getValue()] = new ArrayList<Integer>();
      }
      frequencyList[entry.getValue()].add(entry.getKey());
    }
    int counter = 0;
    int[] output = new int[k];
    for (int i = nums.length; i >= 0 && counter < k; i--) {
      if (frequencyList[i] != null) {
        while (counter < k && frequencyList[i].size() > 0) {
          output[counter] = frequencyList[i].remove(0); // heavy operation
          counter++;
        }
      }
    }
    return output;
  }
}

class Solution {
  public class NumFrequency {
    int num;
    int frequency;

    NumFrequency(int xnum, int xfrequency) {
      num = xnum;
      frequency = xfrequency;
    }
  }

  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> frequencyMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      if (frequencyMap.containsKey(nums[i])) {
        frequencyMap.put(nums[i], frequencyMap.get(nums[i]) + 1);
      } else {
        frequencyMap.put(nums[i], 1);
      }
    }

    PriorityQueue<NumFrequency> minHeap = new PriorityQueue<>(
        (a, b) -> Integer.compare(a.frequency, b.frequency));

    for (int key : frequencyMap.keySet()) {
      if (minHeap.size() < k) {
        minHeap.add(new NumFrequency(key, frequencyMap.get(key)));
      } else {
        minHeap.add(new NumFrequency(key, frequencyMap.get(key)));
        minHeap.poll();
      }
    }

    // can be done without class
    // PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(
    // (a, b) -> (b.getValue() - a.getValue()));
    // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    // maxHeap.add(entry);
    // }

    int[] output = new int[k];
    int counter = k - 1;
    while (minHeap.size() > 0) {
      output[counter] = minHeap.poll().num;
      counter--;
    }
    return output;

  }
}