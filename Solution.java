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
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
    for (int num : nums) {
      frequencyMap.put(num, frequencyMap.getOrDefault(num, 1) + 1);
    }
    List<Integer>[] frequencyList = new List[nums.length + 1];

    for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
      if (frequencyList[entry.getValue()] == null) {
        frequencyList[entry.getValue()] = new ArrayList<Integer>();
      }
      frequencyList[entry.getValue()].add(entry.getKey());
    }
    int counter = 0;
    int[] output = new int[k];
    for (int i = nums.length; i >= 0 && counter < k; i--) {
      if (frequencyList[i] != null) {
        while (counter < k && frequencyList[i].size() > 0) {
          output[counter] = frequencyList[i].remove(0);
          counter++;
        }
      }
    }
    return output;
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

    int[] ans = solution.topKFrequent(input, 2);
    System.out.println(ans.toString());
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
