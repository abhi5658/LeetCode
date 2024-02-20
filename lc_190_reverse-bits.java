public class Solution {
  // you need treat n as an unsigned value
  public int reverseBits(int n) {
    int res = 0;
    for (int i = 0; i < 32; i++) {
      int bitAtIndex = n & 1;

      // option 1 - shift bit to be added to appropriate position before adding
      // bitAtIndex = bitAtIndex << (31 - i);

      // option 2 - shift the res by one and always add bit at first place
      res = res << 1;

      res = res | bitAtIndex;
      n = n >> 1;
    }
    return res;
  }
}