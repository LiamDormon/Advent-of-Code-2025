package uk.dormon.day1;

import java.util.List;

public class Main {

    public static int solvePart1(List<Rotation> rotations) {
        int[] dial = new int[100]; // Dial with 100 positions (0-99)
        int currentPositionIdx = 50;
        int zeroCount = 0; // Count of how many times we've landed on position 0

        // Initialise a dial with 101 positions
        for (int i = 0; i < dial.length; i++) {
            dial[i] = i;
        }

        for (Rotation r : rotations) {
            int steps = r.steps();

            if (r.direction() == Direction.LEFT) {
                currentPositionIdx = (currentPositionIdx - steps) % dial.length;
                if (currentPositionIdx < 0) currentPositionIdx += dial.length; // wrap negative indices to valid range
            } else if (r.direction() == Direction.RIGHT) {
                currentPositionIdx = (currentPositionIdx + steps) % dial.length;
            }

            // Check if we've landed on position 0
            if (dial[currentPositionIdx] == 0) {
                zeroCount++;
            }
        }

        return zeroCount;
    }

    static void main() {
        List<Rotation> rotations = PuzzleInputParser.parseInput();

        int part1Solution = solvePart1(rotations);

        System.out.printf("Part 1 solution: %s%n", part1Solution);
    }
}
