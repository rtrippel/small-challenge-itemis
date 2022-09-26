import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RequestHandlerTest {

    private static Stream<Arguments> provideInitializationOperations() {

        return Stream.of(
                Arguments.of("glob is I", "glob", "I"),
                Arguments.of("prok is V", "prok", "V"),
                Arguments.of("pish is X", "pish", "X"),
                Arguments.of("tegj is L", "tegj", "L")
        );
    }
    private static Stream<Arguments> provideDeterminingOperations() {

        return Stream.of(
                Arguments.of("glob glob Silver is 34 Credits", "Silver", 17),
                Arguments.of("glob prok Gold is 57800 Credits", "Gold", 14450),
                Arguments.of("pish pish Iron is 3910 Credits", "Iron", 195.5)
        );
    }
    private static Stream<Arguments> provideCalculateOperationsHowMuch() {

        return Stream.of(
                Arguments.of("how much is pish tegj glob glob ?", "pish tegj glob glob is 42"),
                Arguments.of("how much is tegj glob glob glob ?", "tegj glob glob glob is 53")
        );
    }
    private static Stream<Arguments> provideCalculateOperationsHowMany() {

        return Stream.of(
                Arguments.of("how many Credits is glob prok Silver ?", "glob prok Silver is 68 Credits"),
                Arguments.of("how many Credits is glob prok Gold ?", "glob prok Gold is 57800 Credits"),
                Arguments.of("how many Credits is glob prok Iron ?", "glob prok Iron is 782 Credits")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInitializationOperations")
    public void testExecuteInitializationOperationSuccess(String operation, String iUnit, String romanNumeral) {

        new RequestHandler().execute(operation);
        String result = IntergalacticUnit.getInstance().getValue(iUnit);

        assertEquals(romanNumeral, result);
    }

    @ParameterizedTest
    @MethodSource("provideDeterminingOperations")
    public void testExecuteDeterminingOperationSuccess(String operation, String resourceName, double cost) {
        IntergalacticUnit.getInstance().addConformity("glob", "I");
        IntergalacticUnit.getInstance().addConformity("prok", "V");
        IntergalacticUnit.getInstance().addConformity("pish", "X");

        new RequestHandler().execute(operation);
        double result = Resource.getInstance().getCost(resourceName);

        assertEquals(cost, result);
    }

    @ParameterizedTest
    @MethodSource("provideCalculateOperationsHowMuch")
    public void testExecuteCalculationOperationWithHowMuchSuccess(String calculationOperation, String expected) {
        IntergalacticUnit.getInstance().addConformity("glob", "I");
        IntergalacticUnit.getInstance().addConformity("prok", "V");
        IntergalacticUnit.getInstance().addConformity("pish", "X");
        IntergalacticUnit.getInstance().addConformity("tegj", "L");
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        new RequestHandler().execute(calculationOperation);

        verify(out, times(1)).println(expected);
    }

    @ParameterizedTest
    @MethodSource("provideCalculateOperationsHowMany")
    public void testExecuteCalculationOperationWithHowManySuccess(String calculationOperation, String expected) {
        IntergalacticUnit.getInstance().addConformity("glob", "I");
        IntergalacticUnit.getInstance().addConformity("prok", "V");
        IntergalacticUnit.getInstance().addConformity("pish", "X");
        IntergalacticUnit.getInstance().addConformity("tegj", "L");
        Resource.getInstance().addResourceNameAndCoast("Silver", 17D);
        Resource.getInstance().addResourceNameAndCoast("Gold", 14450D);
        Resource.getInstance().addResourceNameAndCoast("Iron", 195.5);

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        new RequestHandler().execute(calculationOperation);

        verify(out, times(1)).println(expected);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "dsfewfw we is 2222222222",
            ":!13r12e"})
    public void testExecuteCalculationOperationWithHowManySuccess(String unknownOperation) {
        final String UNKNOWN_RESPONSE = "I have no idea what you are talking about";

        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        new RequestHandler().execute(unknownOperation);

        verify(out, times(1)).println(UNKNOWN_RESPONSE);
    }

    @Test
    public void testExecuteUnknownIUnitNameFail() {
        String incorrectOperation = "glob glob kkk Silver is 34 Credits";
        String expectedMessage = "Incorrect names entered, initialize them or re-enter the correct query.";
        IntergalacticUnit.getInstance().addConformity("glob", "I");
        IntergalacticUnit.getInstance().addConformity("prok", "V");
        IntergalacticUnit.getInstance().addConformity("pish", "X");
        PrintStream out = mock(PrintStream.class);
        System.setOut(out);

        new RequestHandler().execute(incorrectOperation);

        verify(out, times(1)).println(expectedMessage);
    }
}
