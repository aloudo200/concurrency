package org.personal.concurrency.exercises.neetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class ValidAnagram {

    public static void main (String[] args) {

        System.out.println(validAnagram("racecar", "carrace"));

    }

    private static boolean validAnagram(String a, String b) {
        Map<Character, Integer> frequencyA = new HashMap<>();
        Map<Character, Integer> frequencyB = new HashMap<>();

        for (char c : a.toCharArray()) {
            frequencyA.put(c, frequencyA.getOrDefault(c, 0) + 1);
        }

        for (char c : b.toCharArray()) {
            frequencyB.put(c, frequencyB.getOrDefault(c, 0) + 1);
        }

        return frequencyA.equals(frequencyB);
    }

//    public List<List<String>> groupAnagrams(String[] strs) {
//
//        //    HashMap<Character, Integer> alphabet = new HashMap<>();
//
//        //    for(char c = 'a'; c <= 'z'; c++) {
//        //    charMap.put(c, 0);
//        //    }
//
//        HashMap<String, Integer> charSums = new HashMap<>();
//
//        ArrayList<String> groupedAnagrams = new ArrayList<>();
//
//        for(String word : strs) {
//            charSums.put(word, word.chars().sum());
//
//        }
//
//
//
//
//    }

}
