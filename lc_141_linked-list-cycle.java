/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
  // e.g. list 1-10 end points to 1
  // s -> 1 2 3 4 5 6 7 8 9 10 1
  // f -> 1 3 5 7 9 1 3 5 7 9 1

  // e.g. list 1-9 end points to 1
  // s -> 1 2 3 4 5 6 7 8 9 1
  // f -> 1 3 5 7 9 2 4 6 8 1
  public boolean hasCycle(ListNode head) { // O(<=n)
    if (head == null)
      return false;
    ListNode slow = head;
    ListNode fast = head;
    while (fast.next != null && fast.next.next != null) {
      slow = slow.next;
      fast = fast.next.next;
      if (slow == fast) {
        return true;
      }
    }
    return false;
  }
}

/**
 * Definition for singly-linked list.
 * class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) {
 * val = x;
 * next = null;
 * }
 * }
 */
public class Solution {
  public boolean hasCycle(ListNode head) { // O(n)
    Set<String> set = new HashSet<>();
    while (head != null) {
      if (set.contains(head.toString())) {
        return true;
      } else {
        set.add(head.toString());
      }
      head = head.next;
    }
    return false;
  }
}