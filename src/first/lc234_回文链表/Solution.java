package lc234_回文链表;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        boolean isOdd = false;
        while (fast != null) {
            if (fast.next != null) {
                if (fast.next.next != null) {
                    fast = fast.next.next;
                    slow = slow.next;
                } else {
                    fast = fast.next;
                    slow = slow.next;
                    break;
                }
            } else {
                isOdd = true;
                break;
            }
        }
        if (isOdd) {
            fast = reverse(slow.next);
        } else {
            fast = reverse(slow);
        }
        slow = head;
        while (fast != null) {
            if (fast.val != slow.val) {
                return false;
            }
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode t = cur.next;
            cur.next = pre;
            pre = cur;
            cur = t;
        }
        return pre;
    }
}


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