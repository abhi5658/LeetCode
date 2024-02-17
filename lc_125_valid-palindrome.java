class Solution {
  public boolean isPalindrome(String s) {
    int start = 0;
    int end = s.length() - 1;
    while (start <= end) {
      Character left = s.charAt(start);
      Character right = s.charAt(end);
      if (!Character.isAlphabetic(left) && !Character.isDigit(left)) {
        start++;
        continue;
      }
      if (!Character.isAlphabetic(right) && !Character.isDigit(right)) {
        end--;
        continue;
      }
      if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
        return false;
      }
      start++;
      end--;
    }

    return true;
  }
}
