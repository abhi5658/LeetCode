class Solution { // 6 ms
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      String str = strs[i];
      char[] stringCharacters = str.toCharArray();
      Arrays.sort(stringCharacters);
      String sortedKey = new String(stringCharacters);

      List<String> findGroup = groups.get(sortedKey);
      if (groups.containsKey(sortedKey)) {
        groups.get(sortedKey).add(str);
      } else {
        List<String> newGroupList = new ArrayList<String>();
        newGroupList.add(str);
        groups.put(sortedKey, newGroupList);
      }
    }
    List<List<String>> output = new ArrayList<>();
    for (List<String> value : groups.values()) {
      output.add(value);
    }
    return output;
  }
}

class Solution { // 7 ms
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      char[] charCount = new char[26]; // default char would be present
      String str = strs[i];
      for (int j = 0; j < str.length(); j++) {
        charCount[str.charAt(j) - 'a']++;
      }
      String countHash = String.valueOf(charCount); // directly joining as string
      // IntStream.of(charCount).mapToObj(x ->
      // String.valueOf(x)).collect(Collectors.joining("-"));
      // String.join("-", charCount);
      List<String> findGroup = groups.get(countHash);
      if (findGroup != null) {
        findGroup.add(str);
      } else {
        // to create mutable list use new ArrayList -
        // https://stackoverflow.com/a/1005089/8133717
        groups.put(countHash, new ArrayList<>(Arrays.asList(str)));
      }
    }
    List<List<String>> output = new ArrayList<>();
    for (List<String> value : groups.values()) {
      output.add(value);
    }
    return output;
  }
}

class Solution { // 44 ms
  public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> groups = new HashMap<>();
    for (int i = 0; i < strs.length; i++) {
      int[] charCount = new int[26];
      String str = strs[i];
      for (int j = 0; j < str.length(); j++) {
        charCount[str.charAt(j) - 'a']++;
      }
      String countHash = IntStream.of(charCount).mapToObj(x -> String.valueOf(x)).collect(Collectors.joining("-"));
      // String.join("-", charCount);
      List<String> findGroup = groups.get(countHash);
      if (findGroup != null) {
        findGroup.add(str);
      } else {
        // to create mutable list use new ArrayList -
        // https://stackoverflow.com/a/1005089/8133717
        groups.put(countHash, new ArrayList<>(Arrays.asList(str)));
      }
    }
    List<List<String>> output = new ArrayList<>();
    for (List<String> value : groups.values()) {
      output.add(value);
    }
    return output;
  }
}