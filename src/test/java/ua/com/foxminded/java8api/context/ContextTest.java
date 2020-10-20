package ua.com.foxminded.java8api.context;

import org.junit.jupiter.api.Test;
import ua.com.foxminded.java8api.collector.DataCollector;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ContextTest {

    Context context = new Context();

    @Test
    void getObject_ShouldReturnObject_WhenInputClass() {
        DataCollector dataCollector = new DataCollector();
        assertSame(dataCollector.getClass(), context.getObject(DataCollector.class).getClass());
    }

    @Test
    void getObject_ShouldThrowException() throws Exception {
        Exception mockedException = mock(InstantiationException.class);
        Logger mockedLogger = mock(Logger.class);

        class Dummy {

            public Dummy() throws Exception {
                throw mockedException;
            }
        }

        try {
            Dummy dummy = context.getObject(Dummy.class);
        } catch (Exception e) {
            verify(mockedLogger, times(1)).log(Level.INFO, anyString());
        }
    }
}
