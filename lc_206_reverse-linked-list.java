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
class Solution {
  public ListNode reverseList(ListNode head) {
    ListNode node = head;
    ListNode previousNode = null;
    while (node != null) {
      ListNode nextNode = node.next; // null
      node.next = previousNode; // 4
      previousNode = node; // 5
      node = nextNode; // null
    }
    return previousNode;
  }
}
