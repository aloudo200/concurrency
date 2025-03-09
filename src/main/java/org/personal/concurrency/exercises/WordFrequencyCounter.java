package org.personal.concurrency.exercises;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordFrequencyCounter {

    private static final Logger log = LoggerFactory.getLogger(WordFrequencyCounter.class);

    static final int NUM_THREADS = 4;  // Number of threads

    public static void main(String[] args) throws IOException {
        // Read the text file from classpath
        String text = readTextFromFile("input.txt");  // Ensure "input.txt" is in resources/classpath

        // Split text into parts (Avoid cutting words in half)
        List<String> textParts = splitTextSmartly(text, NUM_THREADS);

        // ExecutorService for thread management
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        // Submit tasks for each text part
        for (String part : textParts) {
            futures.add(executor.submit(new WordCountTask(part)));
        }

        // Use ConcurrentHashMap for efficient merging
        ConcurrentHashMap<String, Integer> wordFrequency = new ConcurrentHashMap<>();

        futures.parallelStream().forEach(future -> {
            try {
                future.get().forEach((key, value) ->
                        wordFrequency.merge(key, value, Integer::sum)
                );
            } catch (Exception e) {
                log.error("An error occurred when merging the results : {}", e.getMessage());
            }
        });

        // Sort results in descending order and print
        wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));

        // Shutdown executor
        executor.shutdown();
    }

    // Task for counting words in a given text part
    static class WordCountTask implements Callable<Map<String, Integer>> {
        private final String text;

        public WordCountTask(String text) {
            this.text = text;
        }

        @Override
        public Map<String, Integer> call() {
            return Pattern.compile("[^a-zA-Z]+")
                    .splitAsStream(text)
                    .filter(word -> !word.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toConcurrentMap(word -> word, _ -> 1, Integer::sum));
        }
    }

    private static List<String> splitTextSmartly(String text, int numParts) {
        int partLength = text.length() / numParts;

        return java.util.stream.IntStream.range(0, numParts)
                .mapToObj(i -> {
                    int start = (i == 0) ? 0 : text.lastIndexOf(" ", i * partLength); // Find start at space
                    int end = (i == numParts - 1) ? text.length() : text.lastIndexOf(" ", (i + 1) * partLength);

                    // Ensure valid start and end
                    if (start == -1) start = i * partLength; // Fallback if no space found
                    if (end == -1 || end <= start) end = Math.min(start + partLength, text.length());

                    return text.substring(start, end).trim();
                })
                .filter(s -> !s.isEmpty()) // Remove empty segments
                .collect(Collectors.toList());
    }

    // Method to read text from a file in the classpath
    private static String readTextFromFile(String fileName) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("File not found in classpath: " + fileName);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining("\n"));
        }
    }
}
