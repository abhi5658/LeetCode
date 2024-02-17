import java.util.ArrayList;

class Solution {
  public boolean isValid(String s) {
    Stack<Character> stack = new Stack<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '(' || c == '{' || c == '[') { // opening
        stack.push(c);
      } else if (!stack.empty()) {
        char top = stack.peek();
        if ((top == '(' && c == ')')
            || (top == '{' && c == '}')
            || (top == '[' && c == ']')) { // closing with valid
          stack.pop();
        } else { // close with invalid bracket e.g. (]
          return false;
        }
      } else { // directly closing
        return false;
      }
    }
    return stack.empty();
  }
}

class Solution {
  public boolean isValid(String s) {
    int size = s.length();
    if (size % 2 != 0) {
      return false;
    }
    boolean valid = true;
    ArrayList<Character> al = new ArrayList<>();

    for (int i = 0; i < size; i++) {
      char c = s.charAt(i);
      if (c == '{' || c == '(' || c == '[') {
        al.add(0, c);
      } else {
        if (al.size() <= 0) { // "){"
          valid = false;
          break;
        }
        char existingOpening = al.get(0);
        if ((c == '}' && existingOpening == '{')
            || (c == ')' && existingOpening == '(')
            || (c == ']' && existingOpening == '[')) {
          al.remove(0);
          continue;
        } else {
          valid = false;
          break;
        }
      }
    }
    if (al.size() > 0) { // "(("
      return false;
    }
    return valid;
  }

}