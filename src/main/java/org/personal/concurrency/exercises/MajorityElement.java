package org.personal.concurrency.exercises;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement {

    private static List<String> candidates = List.of("a", "a", "a", "b", "b", "b", "b", "c", "c", "c", "c", "c");

    public static void main(String[] args) {

        System.out.println("Majority element is: " + majorityElement(candidates));



    }

    private static String majorityElement(List<String> candidates) {
        int count = 0;
        String candidate = null;

        for (String c : candidates) {
            if (count == 0) {
                candidate = c;

            }
            if (candidate.equalsIgnoreCase(c)) {
                count++;
            } else {
                count--;
            }
        }
        return candidate;
    }

}
