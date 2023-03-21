package barry.diamond;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Print a diamond where the supplied argument
 */
public class Diamond {

    // For example...
//    public static void main(String[] args) {
//        System.out.println(new Diamond().printDiamond('F'));
//    }

    public String printDiamond(final char centralChar) {
        if(centralChar < 'A' || centralChar > 'Z') {
            throw new IllegalArgumentException("'" + centralChar + "' is not a valid central character.");
        }

        // Create an empty line for use in building the diamond
        // Line size is 1 char plus two per row above 'A'
        final String blankLine = " " + IntStream.range('A', centralChar)
                .mapToObj(c -> "  ")
                .collect(Collectors.joining());

        final Deque<String> result = new LinkedList<>();
        final StringBuilder builder = new StringBuilder(blankLine);
        builder.replace(0, 1, String.valueOf(centralChar)); // Central line is a special case
        builder.replace(builder.length()-1, builder.length(), String.valueOf(centralChar));
        result.add(builder.toString());

        // Work out from the centre to make efficient use of the Deque
        for(int c = centralChar - 1; c >= 'A'; c--) {
            builder.replace(0, builder.length(), blankLine);
            final int indent = centralChar - c;
            builder.replace(indent, indent + 1, String.valueOf((char) c));
            builder.replace(builder.length() - indent - 1, builder.length() - indent, String.valueOf((char) c));
            result.addFirst(builder.toString());
            result.addLast(result.getFirst());
        }

        return String.join("\n", result) + "\n";
    }
}
