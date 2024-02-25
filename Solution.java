import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.*;

import org.w3c.dom.Node;

/**
 * vscode: Add the folder to Java Source path before running any file
 * - Right click on folder and select "Add Folder to Java Source Path"
 */

class ListNode {
  int val;
  ListNode next;

  ListNode() {
  }

  ListNode(int val) {
    this.val = val;
  }

  ListNode(int val, ListNode next) {
    this.val = val;
    this.next = next;
  }
}

class Solution {
  public int findMin(int[] nums) {
    int left = 0; //
    int right = nums.length - 1;
    int mid = -1;
    while (left < right) {
      mid = (left + right) / 2;
      if (nums[left] > nums[right]) {
        if (nums[left] < nums[mid]) {
          left = mid;
        } else if (nums[mid] < nums[right]) {
          right = mid;
        } else if (nums[mid] > nums[right]) {
          right = mid;
        } else {
          left = mid + 1;
        }
      } else {
        right = left;
      }
      // if (nums[left] < nums[mid]) { // increasing from left "->/"
      // left = mid;
      // } else if (nums[mid] < nums[right]) { // decreasing from right "/<-"
      // right = mid;
      // } else if(nums[right] >= nums[mid]){
      // right = mid;
      // } else if (nums[left] >= nums[mid]) {
      // left = mid + 1;
      // }
    }
    return nums[(left + right) / 2];
  }
  public static void printList(ListNode list) {
    ListNode currNode = list;

    System.out.print("LinkedList: ");

    // Traverse through the LinkedList
    while (currNode != null) {
      // Print the data at current node
      System.out.print(currNode.val + " ");

      // Go to next node
      currNode = currNode.next;
    }
  }

  public static void main(String[] args) {
    System.out.println("hey");
    Solution solution = new Solution();
    // System.out.println("aa: " + solution.isAnagram("abca", "aabc"));
    int[] input = new int[] { 1, 1, 1, 2, 2, 3 };

    int ans = solution.findMin(new int[] { 3, 4, 5, 1, 2 });
    System.out.println(ans);
    // int[] aa = solution.twoSum(new int[] { 2, 7, 11, 15 }, 9);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 2, 4 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    // aa = solution.twoSum(new int[] { 3, 3 }, 6);
    // System.out.println("aa: " + Arrays.toString(aa));
    // System.out.println("output: " + solution.isValid("()"));
    // System.out.println("output: " + solution.isValid("()[]{}"));
    // System.out.println("output: " + solution.isValid("(]"));
    // System.out.println("output: " + solution.isValid("(("));
    // System.out.println("output: " + solution.isValid("){"));
  }
}
