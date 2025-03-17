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

    static final int NUM_THREADS = 4;  

    public static void main(String[] args) throws IOException {
        
        String text = readTextFromFile("input.txt");  

        // use regex matching to split the text into actual words
        List<String> textParts = splitTextSmartly(text, NUM_THREADS);

        // use a fixed thread pool based on NUM_THREADS
        ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Map<String, Integer>>> futures = new ArrayList<>();

        // once we have all text parts, submit each into a thread
        for (String part : textParts) {
            futures.add(executor.submit(new WordCountTask(part)));
        }       
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

        // sort in descending order
        wordFrequency.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        
        executor.shutdown();
    }

    // task to count words in each split part
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
                    int start = (i == 0) ? 0 : text.lastIndexOf(" ", i * partLength); 
                    int end = (i == numParts - 1) ? text.length() : text.lastIndexOf(" ", (i + 1) * partLength);                  
                    if (start == -1) start = i * partLength;
                    if (end == -1 || end <= start) end = Math.min(start + partLength, text.length());

                    return text.substring(start, end).trim();
                })
                .filter(s -> !s.isEmpty()) // exclude empty portions
                .collect(Collectors.toList());
    }
 
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
