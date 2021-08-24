package com.spring.messaging.PubSubDemo.service;

import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class PalindromeFinder {

    public int getLongestPalindromeSize(final String content) {
        final String trimmed = omitNumbers(content);

        int longest = 0;
        for (int startIndex = 0; startIndex < content.length(); startIndex++) {

            int palindromeSize = getLongestPalindromeFromAllSubstrings(trimmed, startIndex);
            longest = Math.max(longest, palindromeSize);
        }
        return longest;
    }

    private String omitNumbers(final String content) {
        final var sb = new StringBuilder();

        IntStream.range(0, content.length()).forEach(i -> {
            char currentChar = content.charAt(i);
            if (Character.isAlphabetic(currentChar)) {
                sb.append(currentChar);
            }
        });
        return sb.toString().toLowerCase();
    }

    private int getLongestPalindromeFromAllSubstrings(final String content, final int startIndex) {
        int longest = 0;
        for (int i = startIndex; i < content.length(); i++) {
            int stringSliceLength = i + 1 - startIndex;

            if (isSlicePalindrome(content, startIndex, i)) {
                longest = Math.max(longest, stringSliceLength);
            }
        }
        return longest;
    }

    private boolean isSlicePalindrome(final String content, final int startIndex, final int endIndex) {
        final int stringSliceLength = endIndex + 1 - startIndex;

        for (int i = 0; i < stringSliceLength / 2; i++) {
            if (content.charAt(startIndex + i) != content.charAt(endIndex - i)) {
                return false;
            }
        }
        return true;
    }
}