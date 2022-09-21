import org.junit.jupiter.api.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;


class RunApplicationTest {

    @Test
    public void runSuccess() {
        final String welcomeMessage = "Welcome to \"MERCHANT'S GUIDE TO THE GALAXY\"";
        final PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        RunApplication.main(new String[]{});

        verify(out, times(1)).println(welcomeMessage);
    }
}