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
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) { // recursion
    // if(list1 == null && list2 == null)
    if (list1 == null)
      return list2;

    if (list2 == null)
      return list1;

    ListNode temp = null;

    if (list1.val <= list2.val) {
      temp = list1;
      temp.next = mergeTwoLists(list1.next, list2);
    } else {
      temp = list2;
      temp.next = mergeTwoLists(list1, list2.next);
    }
    return temp;
  }
}

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
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    ListNode currentSorted = new ListNode(); // 4 [4] // initialise to handle list1 = [] list2 = [0]
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
    while (pending != null) { // OR directly currentSorted.next = pending; and done
      currentSorted.next = pending;
      pending = pending.next;
      currentSorted = currentSorted.next;
    }
    return head.next;
  }
}