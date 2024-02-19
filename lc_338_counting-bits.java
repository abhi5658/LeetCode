class Solution {
  /**
   * 0 -> 0 = 0
   * 1 -> 01 = 1
   * 2 -> 10 = 1 = 1x2
   * 3 -> 11 = 2
   * 4 -> 100 = 1 = 4 most significant bit
   * 5 -> 101 = 2 = 4 + 1 = 1 + 1
   * 6 -> 110 = 2 = 4 + 2 = 1 + 1
   * 7 -> 111 = 3 = 4 + 3 = 1 + 3
   * 8 -> 1000 = 1 = 8 most significant bit
   * 9 -> 1001 = 2 = 8 + 1 = 1 + 1
   * 10 -> 1010 = 2 = 8 + 2 = 1 + 1
   * 11 -> 1011 = 3 = 8 + 3 = 1 + 3
   * 12 -> 1100 = 2 = 8 + 4 = 1 + 1
   * 13 -> 1101 = 3 = 8 + 5 = 1 + 2
   * 14 -> 1110 = 3 = 8 + 6 = 1 + 2
   * 15 -> 1111 = 4 = 8 + 7 = 1 + 3
   * 16 -> 10000 = 1 = 16 most significant bit
   */
  public int[] countBits(int n) {
    int[] ans = new int[n + 1];
    int mostSignificantBaseTwo = 1;
    ans[0] = 0; // by default 0
    for (int i = 1; i <= n; i++) {
      if (mostSignificantBaseTwo * 2 == i) // whenever we reach next significant bit update mostSignificantBaseTwo
        mostSignificantBaseTwo = i;
      ans[i] = 1 + ans[i - mostSignificantBaseTwo];
    }
    return ans;
  }
}

class Solution {
  public int[] countBits(int n) {
    int[] ans = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      ans[i] = countBitsOfNumber(i, ans);
    }
    return ans;
  }

  public int countBitsOfNumber(int n, int memo[]) {
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;
    if (n == 2)
      return 1;
    if (memo[n] != 0)
      return memo[n];

    if (n % 2 == 0) { // even number
      memo[n] = countBitsOfNumber(n / 2, memo);
      return countBitsOfNumber(n / 2, memo);
    } else { // odd number
      memo[n] = 1 + countBitsOfNumber(n / 2, memo);
      return 1 + countBitsOfNumber(n / 2, memo);
    }
  }
}

class Solution {
  public int[] countBits(int n) {
    int[] ans = new int[n + 1];
    for (int i = 0; i <= n; i++) {
      ans[i] = countBitsOfNumber(i, ans);
    }
    return ans;
  }

  /**
   * 0 -> 0 = 0
   * 1 -> 01 = 1
   * 2 -> 10 = 1 = 2^1
   * 3 -> 11 = 2
   * 4 -> 100 = 1 = 2^2
   * 5 -> 101 = 2 = (4 + 1) = 1 + 1
   * 6 -> 110 = 2 = (4 + 2) = 1 + 1
   * 7 -> 111 = 3 = (4 + 2 + 1) = 1 + 1 + 1
   * 8 -> 1000 = 1 = 2^3
   * 9 -> 1001 = 2 = (8 + 1) = 1 + 1
   * 10 -> 1010 = 2 = (8 + 2) = 1 + 1
   * 11 -> 1011 = 3 = (8 + 2 + 1) = 1 + 1 + 1
   * 12 -> 1100 = 2 = (8 + 4) = 1 + 1
   * 13 -> 1101 = 3 = (8 + 4 + 1) = 1 + 1 + 1
   * 14 -> 1110 = 3 = (8 + 4 + 2) = 1 + 1 + 1
   * 15 -> 1111 = 4 = (8 + 4 + 2 + 1) = 1 + 1 + 1 + 1
   * 16 -> 10000 = 1 = 2^4
   */
  public int countBitsOfNumber(int n, int memo[]) {
    if (n == 0)
      return 0;
    if (n == 1)
      return 1;
    if (n == 2)
      return 1;
    if (memo[n] != 0)
      return memo[n];

    int maxTwoPower = (int) (Math.log(n) / Math.log(2)); // might be expensive

    return 1 + countBitsOfNumber(n - (int) Math.pow(2, maxTwoPower), memo);
  }
}