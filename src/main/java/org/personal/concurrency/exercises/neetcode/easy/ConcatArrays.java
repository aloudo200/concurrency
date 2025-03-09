package org.personal.concurrency.exercises.neetcode.easy;

import java.util.Arrays;

public class ConcatArrays {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(getConcatenation(new int[]{1, 2, 1})));
    }

    public static int[] getConcatenation(int[] nums) {

        int[] result = new int [nums.length * 2 ];

        System.arraycopy(nums, 0, result, 0, nums.length);
        System.arraycopy(nums, 0, result, 0 + nums.length, nums.length);
        return result;

    }

}
