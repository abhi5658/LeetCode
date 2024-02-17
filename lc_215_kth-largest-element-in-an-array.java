class Solution {
  public int findKthLargest(int[] nums, int k) { // O(nlog(k))
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    for (int i = 0; i < nums.length; i++) {
      minHeap.add(nums[i]);
      if (minHeap.size() > k) {
        minHeap.poll();
      }
    }
    return minHeap.poll();
  }
}

class Solution {
  public int findKthLargest(int[] nums, int k) { // O(nlog(n))
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
    for (int i = 0; i < nums.length; i++) {
      maxHeap.add(nums[i]);
    }
    while (maxHeap.size() > nums.length - k + 1)
      maxHeap.poll();
    return maxHeap.poll();
  }
}