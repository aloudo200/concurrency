package org.personal.concurrency.exercises.dsa;

import java.util.Arrays;
import java.util.Stack;
import java.util.stream.Collectors;

public class ReverseWordsInSentence {

    public static void main(String[] args) {

        ReverseWordsInSentence reverseWordsInSentence = new ReverseWordsInSentence();

        System.out.println(reverseWordsInSentence.reverseSentence("i am going to be reversed"));

    }

    public String reverseSentence(String input) {

        Stack<String> wordStack = Arrays.stream(input.split(" ")).collect(Collectors.toCollection(Stack::new));

        StringBuilder sb = new StringBuilder();

        while (!wordStack.isEmpty()) {
            sb.append(wordStack.pop()).append(" ");
        }

        return sb.toString();

    }

}
