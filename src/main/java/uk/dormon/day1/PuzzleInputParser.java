package uk.dormon.day1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInputParser {
    public static List<Rotation> parseInput() {
        List<Rotation> rotations = new ArrayList<>();

        try(InputStream stream = PuzzleInputParser.class.getResourceAsStream("/day1.txt")){
            if (stream == null) {
                System.out.println("Puzzle input not found");
                throw new Exception("Puzzle input not found");
            }

            String[] lines = new String(stream.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8).split("\\R");

            for (String line : lines) {
                if (line == null || line.isBlank()) continue;

                char startLetter = line.charAt(0);
                int steps = Integer.parseInt(line.substring(1).trim());

                switch (startLetter) {
                    case 'L' -> {
                        rotations.add(new Rotation(Direction.LEFT, steps));
                    }
                    case 'R' -> {
                        rotations.add(new Rotation(Direction.RIGHT, steps));
                    }
                    default -> throw new Exception("Invalid direction in input: " + startLetter);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to read puzzle input: " + e.getMessage());
        }

        return rotations;
    }
}
