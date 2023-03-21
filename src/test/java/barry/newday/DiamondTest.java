package barry.newday;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.*;

class DiamondTest {
    private Diamond diamond;

    @BeforeEach
    public void setUp() {
        diamond = new Diamond();
    }

    @AfterEach
    public void tearDown() {
        diamond = null;
    }

    @ParameterizedTest
    @MethodSource("testCaseProvider")
    public void testExecutesForSingleCharacter(final char centralChar, final String expectedResult) {
        final String result = diamond.printDiamond(centralChar);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testResultIsASquareOfExpectedSize() {
        for(char c = 'A'; c <= 'Z'; c++) {
            final int square = 1 + (c - 'A') * 2;
            String[] lines = diamond.printDiamond(c).split("\n");
            assertEquals(square, lines.length);
            assertEquals(Set.of(square), Arrays.stream(lines)
                    .map(String::length)
                    .collect(toSet()));
        }
    }

    @Test
    public void testExceptionForInvalidCharacter() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> diamond.printDiamond('0'));
        assertEquals("'0' is not a valid central character.", exception.getMessage());
    }

    public static Stream<Arguments> testCaseProvider() {
        return Stream.of(
                Arguments.of('A', "A\n"),
                Arguments.of('B', " A \nB B\n A \n"),
                Arguments.of('C', "  A  \n B B \nC   C\n B B \n  A  \n")
        );
    }
}