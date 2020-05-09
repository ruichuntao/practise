package rui.leetcode;

import java.util.Random;

public class RandomList {
    private ListNode head;
    public RandomList(ListNode head) {
        this.head = head;
    }

    public int getRandom() {
        int res = head.val;
        ListNode no = head.next;
        int i = 2;
        Random random = new Random();
        while(no!=null){
            if(random.nextInt(i) == 0){
                res = no.val;
            }
            i++;
            no = no.next;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode node = new ListNode(1);
        node.next = new ListNode(1);
        node.next.next = new ListNode(3);
        node.next.next.next = new ListNode(3);
        node.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next = new ListNode(4);
        node.next.next.next.next.next.next = new ListNode(5);
        node.next.next.next.next.next.next.next = new ListNode(5);
        RandomList list = new RandomList(node);
        for (int i = 0; i < 100; i++) {
            System.out.println(list.getRandom());
        }
    }
}
