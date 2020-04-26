/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //
        ListNode answer = null;
        ListNode currentAnswerNode = null;
        ListNode currentNodeA = l1;
        ListNode currentNodeB = l2;
        int total, remainder, carry=0;
        while (currentNodeA != null && currentNodeB != null) {
            total = currentNodeA.val + currentNodeB.val + carry;
            remainder = total % 10;
            carry = total / 10;
            if(answer == null){
                answer = new ListNode(remainder);
                currentAnswerNode = answer;
            }else{
                currentAnswerNode.next = new ListNode(remainder);
                currentAnswerNode = currentAnswerNode.next;
            }
            currentNodeA = currentNodeA.next;
            currentNodeB = currentNodeB.next;
        }

        
        ListNode currentBiggerListNode = null;
        if(currentNodeA != null)
            currentBiggerListNode = currentNodeA;
        else
            currentBiggerListNode = currentNodeB;

        
        while (currentBiggerListNode != null){
            total = currentBiggerListNode.val + carry;
            remainder = total % 10;
            carry = total / 10;
            currentAnswerNode.next = new ListNode(remainder);
            currentAnswerNode = currentAnswerNode.next;
            currentBiggerListNode = currentBiggerListNode.next;
        }
        if(carry>0){
            currentAnswerNode.next = new ListNode(carry);
        }
        return answer;
    }
}