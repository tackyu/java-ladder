package view;

import domain.model.*;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final int MAX_INTERVAL = 6;
    private static final int START_POSITION = 0;
    private static final String VERTICAL_LINE = "|";
    private static final String HORIZONTAL_LINE = "-----";
    private static final String INTERVAL = "     ";

    public void printResult(People people, Ladder ladder) {
        System.out.println("\n실행결과\n");
        printNames(people);

        ladder.getLines()
                .stream()
                .map(line -> printLine(line.findRightConnectedPositions(), people.getNumberOfParticipants()))
                .forEach(System.out::println);
    }


    private String printLine(List<Integer> indexes, int personCount) {
        int maxHorizontalLines = personCount - 1;
        StringBuilder line = new StringBuilder(INTERVAL + VERTICAL_LINE);
        IntStream.range(0, maxHorizontalLines)
                .forEach(index -> line
                        .append(drawHorizontalLine(indexes.contains(index)))
                        .append(VERTICAL_LINE));

        return line.toString();
    }

    private String drawHorizontalLine(boolean hasHorizontalLine) {
        if (hasHorizontalLine) {
            return HORIZONTAL_LINE;
        }
        return INTERVAL;
    }

    private void printNames(People people) {
        people.getParticipants()
                .stream()
                .map(Person::getName)
                .forEach(name -> System.out.print(fillInterval(name)));

        System.out.println();
    }

    private String fillInterval(String name) {
        StringBuilder filledName = new StringBuilder(name);
        return filledName.insert(START_POSITION, " ".repeat(MAX_INTERVAL - name.length()))
                .toString();
    }

}
