package ua.com.foxminded.java8api.collector;

import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataCollector {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    private Map<String, String> nameAbbreviations;
    private Map<String, LocalDateTime> startTime;
    private Map<String, LocalDateTime> endTime;
    private Map<String, String> racersInformation;

    public void collectInformation() {
        setNameAbbreviations("abbreviations.txt");
        setStartTime("start.log");
        setEndTime("end.log");
        returnCompleteRacersInformation();
    }

    private Stream<String> getFile(String fileName) {
        InputStream inputStream = this.getClass().getResourceAsStream("/files/" + fileName);
        InputStreamReader reader = new InputStreamReader(inputStream);

        return new BufferedReader(reader).lines();
    }

    void setNameAbbreviations(String fileName) {
        Stream<String> stream = getFile(fileName);
        nameAbbreviations = stream.collect(Collectors.toMap(
                p -> p.substring(0, 3),
                p -> p.substring(4)
        ));
    }

    void setStartTime(String fileName) {
        Stream<String> stream = getFile(fileName);
        this.startTime = stream.collect(Collectors.toMap(
                p -> p.substring(0, 3),
                p -> LocalDateTime.parse(p.substring(3), formatter)
        ));

    }

    void setEndTime(String fileName) {
        Stream<String> stream = getFile(fileName);
        endTime = stream.collect(Collectors.toMap(
                p -> p.substring(0, 3),
                p -> LocalDateTime.parse(p.substring(3), formatter)
        ));
    }

    private Map<String, Duration> setMapOfRacersLapDuration() {
        return startTime.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey,
                m -> Duration.between(m.getValue(), endTime.get(m.getKey()))
        ));
    }

    private Map<String, Duration> getSortedRacersLapDuration() {
        return setMapOfRacersLapDuration().entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));
    }

    private Map<String, String> sortRacersInformation() {
        return getSortedRacersLapDuration().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        v -> nameAbbreviations.get(v.getKey()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));

    }

    void returnCompleteRacersInformation() {
        racersInformation = sortRacersInformation().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        p -> p.getValue().concat("_" + getSortedRacersLapDuration().get(p.getKey()).toString()),
                        (v1, v2) -> v1,
                        LinkedHashMap::new
                ));
    }

    public Map<String, String> getRacersInformation() {
        return racersInformation;
    }
}
