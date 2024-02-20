import java.util.ArrayList;
import org.w3c.dom.Node;

/**
 * vscode: Add the folder to Java Source path before running any file
 * - Right click on folder and select "Add Folder to Java Source Path"
 */
public class Solution {
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

  public int[] twoSum(int[] nums, int target) {
    int size = nums.length;
    int indexOne = -1, indexTwo = -1;

    for (int i = 0; i < size; i++) {
      int firstElement = nums[i];
      for (int j = i + 1; j < size; j++) {
        int secondElement = nums[j];
        if (firstElement + secondElement == target) {
          indexOne = i;
          indexTwo = j;
          break;
        }
      }
      if (indexOne > -1 && indexTwo > -1) {
        break;
      }
    }
    return new int[] { indexOne, indexTwo };
  }

  public static void main(String[] args) {
    System.out.println("hey");
    Solution solution = new Solution();
    // int[] aa = solution.twoSum(new int[] { 2, 7, 11, 15 }, 9);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 2, 4 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 3 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    System.out.println("output: " + solution.isValid("()"));
    System.out.println("output: " + solution.isValid("()[]{}"));
    System.out.println("output: " + solution.isValid("(]"));
    System.out.println("output: " + solution.isValid("(("));
    System.out.println("output: " + solution.isValid("){"));
  }
}
