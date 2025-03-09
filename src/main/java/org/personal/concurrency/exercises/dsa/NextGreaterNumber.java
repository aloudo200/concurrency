package org.personal.concurrency.exercises.dsa;

public class NextGreaterNumber {

    public static void main(String[] args) {

        System.out.println(nextGreaterNumber("12443252343"));

    }

    public static String nextGreaterNumber(String input) {
        if (input.length() == 1) {
            return "Not possible with single digit input";
        }

        char[] charArray = input.toCharArray();
        int fromEnd;

        // Step 1: Find the first decreasing element from the end
        for (fromEnd = charArray.length - 1; fromEnd > 0; fromEnd--) {
            if (charArray[fromEnd] > charArray[fromEnd - 1]) {
                break;
            }
        }

        if (fromEnd == 0) {
            return "Not possible since elements are in decreasing order already";
        }

        // Step 2: Find the smallest element greater than the element at i-1
        int x = charArray[fromEnd - 1], smallest = fromEnd;
        for (int j = fromEnd + 1; j < charArray.length; j++) {
            if (charArray[j] > x && charArray[j] <= charArray[smallest]) {
                smallest = j;
            }
        }

        // Step 3: Swap the found smallest element with the element at i-1
        char temp = charArray[fromEnd - 1];
        charArray[fromEnd - 1] = charArray[smallest];
        charArray[smallest] = temp;

        // Step 4: Reverse the elements after i-1
        reverse(charArray, fromEnd, charArray.length - 1);

        return new String(charArray);
    }

    // Utility function to reverse a portion of a character
    // array
    private static void reverse(char[] arr, int start, int end)
    {
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

}
