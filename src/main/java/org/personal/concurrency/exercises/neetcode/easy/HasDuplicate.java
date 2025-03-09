package org.personal.concurrency.exercises.neetcode.easy;

import java.util.Arrays;
import java.util.HashSet;

public class HasDuplicate {

    public static void main(String[] args) {

        System.out.println(hasDuplicate(new int[]{1, 2, 3, 1}));
        System.out.println(hasDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println(hasDuplicate(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));

    }

    private static boolean hasDuplicate(int[] nums) {
        HashSet<Integer> numset = Arrays.stream(nums).collect(HashSet::new, HashSet::add, HashSet::addAll);
        return nums.length > numset.size();
    }

}
