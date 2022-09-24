import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestRecipientTest {

    private static Stream<Arguments> provideRequestsFromTaskDescription() {

        return Stream.of(
                Arguments.of("glob is I", "glob is I"),
                Arguments.of("prok is V", "prok is V"),
                Arguments.of("pish is X", "pish is X"),
                Arguments.of("glob glob Silver is 34 Credits", "glob glob Silver is 34 Credits"),
                Arguments.of("glob prok Gold is 57800 Credits", "glob prok Gold is 57800 Credits"),
                Arguments.of("pish pish Iron is 3910 Credits", "pish pish Iron is 3910 Credits"),
                Arguments.of("how much is pish tegj glob glob ?", "how much is pish tegj glob glob ?"),
                Arguments.of("how many Credits is glob prok Silver ?", "how many Credits is glob prok Silver ?"),
                Arguments.of("how many Credits is glob prok Gold ?", "how many Credits is glob prok Gold ?"),
                Arguments.of("how many Credits is glob prok Iron ?", "how many Credits is glob prok Iron ?")
        );
    }


    @ParameterizedTest
    @MethodSource("provideRequestsFromTaskDescription")
    public void testGetRequestSuccess(String expected, String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result =  new RequestRecipient().getRequest();

        assertEquals(expected, result);
    }

}
