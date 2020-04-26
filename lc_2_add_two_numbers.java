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
        ListNode answer = new ListNode(0); // dummy head to start with
        ListNode currentAnswerNode = answer;
        ListNode currentNodeA = l1;
        ListNode currentNodeB = l2;
        int total, remainder, carry=0, x=0, y=0;
        
        while (currentNodeA != null || currentNodeB != null) {
            x = (currentNodeA != null) ? currentNodeA.val : 0;
            y = (currentNodeB != null) ? currentNodeB.val : 0;
            total = x + y + carry;
            remainder = total % 10;
            carry = total / 10;
            
            currentAnswerNode.next = new ListNode(remainder);
            currentAnswerNode = currentAnswerNode.next;
            
            if(currentNodeA != null) currentNodeA = currentNodeA.next;
            if(currentNodeB != null) currentNodeB = currentNodeB.next;
        }

        if(carry>0){
            currentAnswerNode.next = new ListNode(carry);
        }
        
        return answer.next; // sending list skipping dummy node
    }
}