package uk.dormon.day1;

import java.util.List;

public class Day01 {

    public static int solvePart1(List<Rotation> rotations) {
        int[] dialPositions = new int[100]; // Dial with 100 positions (0-99)
        int currentPositionIndex = 50;
        int landedOnZeroCount = 0; // Count of how many times we've landed on position 0

        // Initialise a dial with 100 positions
        for (int i = 0; i < dialPositions.length; i++) {
            dialPositions[i] = i;
        }

        for (Rotation r : rotations) {
            int steps = r.steps();

            if (r.direction() == Direction.LEFT) {
                currentPositionIndex = (currentPositionIndex - steps) % dialPositions.length;
                if (currentPositionIndex < 0) currentPositionIndex += dialPositions.length; // wrap negative indices to valid range
            } else if (r.direction() == Direction.RIGHT) {
                currentPositionIndex = (currentPositionIndex + steps) % dialPositions.length;
            }

            // Check if we've landed on position 0
            if (dialPositions[currentPositionIndex] == 0) {
                landedOnZeroCount++;
            }
        }

        return landedOnZeroCount;
    }

    public static int solvePart2(List<Rotation> rotations) {
        int dialSize = 100;
        int currentPositionIndex = 50;
        int zeroCrossings = 0;

        for (Rotation r : rotations) {
            int steps = r.steps();
            int previousIndex = currentPositionIndex;

            int zeroCrossingsThisMove = 0;
            if (steps > 0) {
                if (r.direction() == Direction.RIGHT) {
                    int firstStepToZero = (dialSize - (previousIndex % dialSize)) % dialSize; // in 0..dialSize-1
                    if (firstStepToZero == 0) firstStepToZero = dialSize; // if already aligned, first hit is at t = dialSize
                    if (steps >= firstStepToZero) {
                        zeroCrossingsThisMove = 1 + (steps - firstStepToZero) / dialSize;
                    }

                    currentPositionIndex = (previousIndex + steps) % dialSize;
                } else { // LEFT
                    int firstStepToZero = previousIndex % dialSize; // in 0..dialSize-1
                    if (firstStepToZero == 0) firstStepToZero = dialSize; // if already aligned, first hit is at t = dialSize
                    if (steps >= firstStepToZero) {
                        zeroCrossingsThisMove = 1 + (steps - firstStepToZero) / dialSize;
                    }

                    // Negate and wrap around
                    currentPositionIndex = ((previousIndex - steps) % dialSize + dialSize) % dialSize;
                }
            }

            zeroCrossings += zeroCrossingsThisMove;
        }

        return zeroCrossings;
    }

    static void main() {
        List<Rotation> rotations = PuzzleInputParser.parseInput();

        int part1Solution = solvePart1(rotations);
        int part2Solution = solvePart2(rotations);

        System.out.printf("Part 1 solution: %s%n", part1Solution);
        System.out.printf("Part 2 solution: %s%n", part2Solution);
    }
}
