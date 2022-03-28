package ru.job4j.profession;

import java.util.*;

public class SortArrayByParityII {

    public int[] sortArrayByParityII(int[] nums) {
        Map<String, List<Integer>> numbers = new HashMap<>();
        numbers.put("odd", new ArrayList<>());
        numbers.put("even", new ArrayList<>());
        for (int num : nums) {
            if (num % 2 == 0) {
                numbers.get("even").add(num);
            } else {
                numbers.get("odd").add(num);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i % 2 == 0) {
                nums[i] = numbers.get("even").remove(0);
            } else {
                nums[i] = numbers.get("odd").remove(0);
            }
        }
        return nums;
    }

//    /**
//     * Definition for singly-linked list.
//     * public class ListNode {
//     *     int val;
//     *     ListNode next;
//     *     ListNode() {}
//     *     ListNode(int val) { this.val = val; }
//     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//     * }
//     */
//    public class ListNode {
//        int val;
//        ListNode next;
//        ListNode() {}
//        ListNode(int val) { this.val = val; }
//        ListNode(int val, ListNode next) { this.val = val;
//            this.next = next; }
//    }
//    class Solution {
//        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
//
//        }
//    }

}
