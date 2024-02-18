class Solution {
  Map<Integer, Integer> climbed = new HashMap<>();

  public int climbStairs(int n) {
    // e.g. n = 5
    // 0 1 2 3 4 5 - now find ways to reach 5 from 5, 4, 3, 2, 1, 0
    // 5->5 = 0
    // 4->5 = 1
    // 3->5 = [3->4 then 4->5] + 3->5 = 1 + 1 = 2
    // 2->5 = [2->3 then 3->5] + [2->4 then 4->5] = 2 + 1 = 3
    // 1->5 = [1->2 then 2->5] + [1->3 then 3->5] = 3 + 2 = 5
    // 0->5 = [0->1 then 1->5] + [0->2 then 2->5] = 5 + 3 = [8]
    // 8 <- 5 <- 3 <- 2 <- 1 <- 0
    if (n == 0 || n == 1)
      return 1;
    if (climbed.containsKey(n))
      return climbed.get(n);

    int calc = climbStairs(n - 1) + climbStairs(n - 2);
    climbed.put(n, calc);
    return calc;
  }
}
