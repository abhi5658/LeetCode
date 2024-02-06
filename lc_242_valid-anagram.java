class Solution {
  public boolean isAnagram(String s, String t) {
    HashMap<Character, Integer> letters = new HashMap<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      Integer count = letters.get(c);
      if (count != null) {
        letters.put(c, count.intValue() + 1);
      } else {
        letters.put(c, 1);
      }
    }
    for (int i = 0; i < t.length(); i++) {
      char c = t.charAt(i);
      Integer count = letters.get(c);
      if (count != null) {
        if (count.intValue() == 1) {
          letters.remove(c);
        } else {
          letters.put(c, count.intValue() - 1);
        }
      } else {
        return false;
      }
    }
    return letters.isEmpty();
  }
}
