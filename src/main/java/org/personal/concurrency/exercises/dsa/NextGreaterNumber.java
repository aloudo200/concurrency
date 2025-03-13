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

        /* instead of a bruteforce approach, we only need to stop checking once we find the first ascending element at the end
           i.e.: 1243 -> start checking from '3', and move left to find the next greater number (if it exists) */
        
        for (fromEnd = charArray.length - 1; fromEnd > 0; fromEnd--) {
            if (charArray[fromEnd] > charArray[fromEnd - 1]) {
                break;
            }
        }

        // in this case, return 0, i.e. if we had '4321' as input, we cannot find a number greater than this with the same integers

        if (fromEnd == 0) {
            return "Not possible since elements are in decreasing order already";
        }

        // 2: find the smallest element greater than the element at fromEnd - 1 (continue moving left)
        int x = charArray[fromEnd - 1], smallest = fromEnd;
        for (int j = fromEnd + 1; j < charArray.length; j++) {
            if (charArray[j] > x && charArray[j] <= charArray[smallest]) {
                smallest = j;
            }
        }

        // 3: swap the found smallest element with the element at fromEnd - i
        char temp = charArray[fromEnd - 1];
        charArray[fromEnd - 1] = charArray[smallest];
        charArray[smallest] = temp;

        // 4: reverse the elements after fromEnd - i
        reverse(charArray, fromEnd, charArray.length - 1);

        return new String(charArray);
    }

    // helper function to reverse a portion of a character array
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
