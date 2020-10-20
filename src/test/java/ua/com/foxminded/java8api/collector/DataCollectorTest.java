package ua.com.foxminded.java8api.collector;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;


public class DataCollectorTest {

    private static final Logger LOGGER = Logger.getLogger(DataCollector.class.getName());

    DataCollector dataCollector = new DataCollector();

    @Test
    void collectInformation_ShouldFillNameAbbreviationsVariableWithData_WhenInvoked() throws NoSuchFieldException {
        final Field field = dataCollector.getClass().getDeclaredField("nameAbbreviations");
        field.setAccessible(true);
        dataCollector.setNameAbbreviations("abbreviations.txt");
        assertNotNull(field);
    }

    @Test
    void collectInformation_ShouldFillSetStartTimeVariableWithData_WhenInvoked() throws NoSuchFieldException {
        final Field field = dataCollector.getClass().getDeclaredField("startTime");
        field.setAccessible(true);
        dataCollector.collectInformation();
        assertNotNull(field);
    }

    @Test
    void collectInformation_ShouldFillSetEndTimeVariableWithData_WhenInvoked() throws NoSuchFieldException {
        final Field field = dataCollector.getClass().getDeclaredField("endTime");
        field.setAccessible(true);
        dataCollector.collectInformation();
        assertNotNull(field);
    }

    @Test
    void collectInformation_ShouldFillRacersInformationVariableWithData_WhenInvoked() throws NoSuchFieldException {
        final Field field = dataCollector.getClass().getDeclaredField("racersInformation");
        field.setAccessible(true);
        dataCollector.collectInformation();
        assertNotNull(field);
    }

    @Test
    void getRacersInformation_ShouldReturnRacersInformation_WhenInvoked() throws NoSuchFieldException {
        final Field field = dataCollector.getClass().getDeclaredField("racersInformation");
        field.setAccessible(true);
        dataCollector.getRacersInformation();
        assertNotNull(field);
    }
}
