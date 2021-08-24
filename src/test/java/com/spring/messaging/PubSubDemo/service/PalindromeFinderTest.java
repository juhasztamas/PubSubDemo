package com.spring.messaging.PubSubDemo.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

public class PalindromeFinderTest {

    private static PalindromeFinder subject;

    @BeforeAll
    public static void setup() {
        subject = new PalindromeFinder();
    }

    @ParameterizedTest
    @MethodSource("providePalindromes")
    public void getLongestPalindromeSize(String input, int expected) {
        final int got = subject.getLongestPalindromeSize(input);
        assertThat(got).isEqualTo(expected);
    }

    private static Stream<Arguments> providePalindromes() {
        return Stream.of(
                Arguments.of("abrakadabra", 3),
                Arguments.of("qwerty", 1),
                Arguments.of("abba", 4),
                Arguments.of("abrakadabra1", 3),
                Arguments.of("abrak1adabra", 3),
                Arguments.of("12345", 0)
        );
    }
}