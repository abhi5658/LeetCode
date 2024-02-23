import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

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

public class Solution {
  /**
   * Definition for singly-linked list.
   * public class ListNode {
   * int val;
   * ListNode next;
   * ListNode() {}
   * ListNode(int val) { this.val = val; }
   * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
   * }
   */

  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode currentSorted = new ListNode(); // 4 [4]
    ListNode head = currentSorted;
    // list1 - null
    // list2 - 4
    while (list1 != null && list2 != null) {
      if (head == null) {
        if (list1.val <= list2.val) {
          head = list1; // 1
          list1 = list1.next;
        } else {
          head = list2;
          list2 = list2.next;
        }
        currentSorted = head;
      } else if (list1.val <= list2.val) {
        currentSorted.next = list1;
        currentSorted = list1;
        list1 = list1.next;
      } else {
        currentSorted.next = list2;
        currentSorted = list2;
        list2 = list2.next;
      }
    }
    ListNode pending = list1 != null ? list1 : (list2 != null) ? list2 : null;
    while (pending != null) {
      currentSorted.next = pending;
      currentSorted = pending;
      pending = pending.next;
    }
    return head;
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
    ListNode a = new ListNode();
    ListNode a1 = a;
    ListNode b = new ListNode();
    ListNode b1 = b;
    int[] c = new int[] {};
    int[] d = new int[] { 0 };
    for (int c2 : c) {
      ListNode x = new ListNode(c2);
      a.next = x;
      a = a.next;
    }
    for (int d2 : d) {
      ListNode x = new ListNode(d2);
      b.next = x;
      b = b.next;
    }
    ListNode ans = solution.mergeTwoLists(a1.next, b1.next);
    printList(ans);
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
