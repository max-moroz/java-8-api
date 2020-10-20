package ua.com.foxminded.java8api.table;

import ua.com.foxminded.java8api.collector.DataCollector;

import java.time.Duration;
import java.util.Comparator;
import java.util.stream.Collectors;


public class TableCreator {

    private static final String WHITESPACE = " ";
    private static final String COLUMN_SEPARATOR = "|";
    private static final String DASH = "-";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    DataCollector dataCollector;

    public TableCreator(DataCollector dataCollector) {
        this.dataCollector = dataCollector;
    }

    public String createTable() {
        dataCollector.collectInformation();

        int[] counter = {1};

        return dataCollector.getRacersInformation().values().stream().limit(15)
                .map(v -> {
                    String[] line = v.split("_");
                    return counter[0]++ + "." + WHITESPACE + line[0] + addWhitespacesToName(line[0], counter[0]) + COLUMN_SEPARATOR +
                            WHITESPACE + line[1] + addWhitespacesToTeamName(line[1]) + COLUMN_SEPARATOR +
                            WHITESPACE + computeLapTime(line[2]) + LINE_SEPARATOR;
                }).collect(Collectors.joining()) +
                addSeparatorLine() + LINE_SEPARATOR +
                dataCollector.getRacersInformation().values().stream().skip(15)
                        .map(v -> {
                            String[] line = v.split("_");
                            return counter[0]++ + "." + WHITESPACE + line[0] + addWhitespacesToName(line[0], counter[0]) + COLUMN_SEPARATOR +
                                    WHITESPACE + line[1] + addWhitespacesToTeamName(line[1]) + COLUMN_SEPARATOR +
                                    WHITESPACE + computeLapTime(line[2]) + LINE_SEPARATOR;
                        }).collect(Collectors.joining());
    }

    private String retrieveTheLongestName() {
        return dataCollector.getRacersInformation().values().stream()
                .map(s -> {
                    String[] line = s.split("_");
                    return line[0];
                }).max(Comparator.comparingInt(String::length)).orElse("0");
    }

    private String retrieveTheLongestTeamName() {
        return dataCollector.getRacersInformation().values().stream()
                .map(s -> {
                    String[] line = s.split("_");
                    return line[1];
                }).max(Comparator.comparingInt(String::length)).orElse("0");
    }

    private String computeLapTime(String lapTime) {
        Duration duration = Duration.parse(lapTime);
        return String.format("%d:%02d.%03d", duration.toMinutes(), duration.getSeconds() % 60, duration.getNano() / (int) Math.pow(10, 6) % 3600);
    }

    private String addWhitespacesToName(String line, int counter) {
        int additionalSpace = 1;
        if (counter <= 10) {
            additionalSpace = 2;
        }
        return retrieveTheLongestName().chars().skip(line.length() - additionalSpace)
                .collect(StringBuilder::new, (s1, s2) -> s1.append(WHITESPACE), StringBuilder::append).toString();
    }

    private String addWhitespacesToTeamName(String line) {
        return retrieveTheLongestTeamName().chars().skip(line.length() - 1)
                .collect(StringBuilder::new, (s1, s2) -> s1.append(WHITESPACE), StringBuilder::append).toString();
    }

    private String addSeparatorLine() {
        String theLongestValue = dataCollector.getRacersInformation().values().stream().max(Comparator.comparingInt(String::length)).orElse("0");
        return theLongestValue.concat("123456").chars().collect(StringBuilder::new, (s1, s2) -> s1.append(DASH), StringBuilder::append).toString();
    }
}
