class TrieNode {
  char value;
  boolean isWord;
  TrieNode[] next;

  public TrieNode(char xvalue) {
    value = xvalue;
    isWord = false;
    next = new TrieNode[26];
  }
}

class Trie {
  TrieNode root;

  public Trie() {
    root = new TrieNode('\0');
  }

  public void insert(String word) {
    TrieNode head = root;
    for (char ch : word.toCharArray()) {
      int letterIndex = ch - 'a';
      if (head.next[letterIndex] != null) {
        head = head.next[letterIndex];
        continue;
      }
      head.next[letterIndex] = new TrieNode(ch);
      head = head.next[letterIndex];
    }
    head.isWord = true;
  }

  public TrieNode traverseToMatchingLeafTrieNode(String prefix) {
    TrieNode head = root;
    for (char ch : prefix.toCharArray()) {
      int letterIndex = ch - 'a';
      if (head.next[letterIndex] == null) {
        return null;
      }
      head = head.next[letterIndex];
    }
    return head;
  }

  public boolean search(String word) {
    // TrieNode leafNode = root;
    // for (char ch : word.toCharArray()) {
    // int letterIndex = ch - 'a';
    // if (head.next[letterIndex] == null) {
    // return false;
    // }
    // head = head.next[letterIndex];
    // }
    // return head.isWord;
    TrieNode leafNode = traverseToMatchingLeafTrieNode(word);
    return leafNode == null ? false : leafNode.isWord;
  }

  public boolean startsWith(String prefix) {
    // TrieNode head = root;
    // for (char ch : prefix.toCharArray()) {
    // int letterIndex = ch - 'a';
    // if (head.next[letterIndex] == null) {
    // return false;
    // }
    // head = head.next[letterIndex];
    // }
    TrieNode leafNode = traverseToMatchingLeafTrieNode(prefix);
    if (leafNode == null) {
      return false;
    }
    if (leafNode.isWord) { // if this node is a word itself
      return true;
    }
    for (TrieNode nextCharacter : leafNode.next) { // find atleast one next character
      if (nextCharacter != null) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */