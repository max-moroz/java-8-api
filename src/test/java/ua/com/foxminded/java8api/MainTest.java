package ua.com.foxminded.java8api;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.java8api.table.TableCreator;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void main_ShouldInvokeCreateTableMethod_UponTheLaunch() {
        String[] args = {};
        TableCreator mockedTableCreator = mock(TableCreator.class);

        Main main = new Main();

        Main.setTableCreator(mockedTableCreator);
        Main.main(args);

        verify(mockedTableCreator, times(1)).createTable();
    }

}
