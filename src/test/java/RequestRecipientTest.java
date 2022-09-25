import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

        String result = new RequestRecipient().getRequest();

        assertEquals(expected, result);
    }

    @Test
    public void testGetRequestWithoutIsFail() {
        String incorrectRequest = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "prok is V ?",
            "how is pish tegj glob glob ?"})
    public void testGetRequestWhenDeterminingOperationWithQuestionMarkWithoutHowManyOrMuchFail(String incorrectRequest) {
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenDeterminingOperationAndCreditStartWithSmallLetterFail() {
        String incorrectRequest = "glob prok Gold is 57800 credits";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenDeterminingOperationAndResourcesNameStartWithSmallLetterFail() {
        String incorrectRequest = "glob prok gold is 57800 Credits";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenDeterminingOperationWithoutCountOfResourceFail() {
        String incorrectRequest = "Silver is 34 Credits";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @EnumSource(RomanNumeral.class)
    public void testGetRequestWhenInitializationOperationAllRomanNumeralSuccess(RomanNumeral romanNumeral) {
        String input = String.format("prok is %s", romanNumeral);
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertEquals(input, result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"i","m", "O", "13", "!"})
    public void testGetRequestWhenInitializationOperationWithIncorrectRomanNumeralFail(String romanNumeral) {
        String input = String.format("prok is %s", romanNumeral);
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"VV","V V", "I I I", "XX", "X X", "CCCCC", "C C C C C C", "III"})
    public void testGetRequestWhenInitializationOperationWithMoreThenOneRomanNumeralFail(String romanNumeral) {
        String input = String.format("prok is %s", romanNumeral);
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"prok glob","glob glob glob", "tegj glob prok"})
    public void testGetRequestWhenInitializationOperationWithMoreThenOneNameOfIntergalacticUnitsFail(String intergalacticUnit) {
        String input = String.format("%s is V", intergalacticUnit);
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenInitializationOperationWithMoreThenOneAndNameOfIntergalacticUnitsStartInUpperCaseFail() {
        String incorrectRequest = "Glob is V";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "how many Credits is glob prok Silver",
            "how much is pish tegj glob glob "})
    public void testGetRequestWhenCalculationOperationWithoutQuestionMarkFail(String incorrectRequest) {
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "how many is glob prok Silver ?",
            "how is glob prok Silver ?",
            "many Credits is glob prok Silver ?",
            "how many credits is glob prok Silver ?",
            "how is pish tegj glob glob ?",
            "is pish tegj glob glob ?",
            "asdas is pish tegj glob glob ?",
            "much is pish tegj glob glob ?"})
    public void testGetRequestWhenCalculationOperationWithoutHowManyCreditsOrHowMuchFail(String incorrectRequest) {
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "how much is?",
            "how much is ?",
            "how much is glob?"})
    public void testGetRequestWhenCalculationOperationWithHowMuchWithoutIUNamesFail(String incorrectRequest) {
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenCalculationOperationWithCreditsInLowerCaseFail() {
        String incorrectRequest = "how many credits is glob prok Gold ?";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @Test
    public void testGetRequestWhenCalculationOperationWithHowMuchAndResourceInLowerCaseFail() {
        String incorrectRequest = "how many Credits is glob prok gold ?";
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "how many Credits is gold ?",
            "how many Credits is  ?",
            "how many Credits is"})
    public void testGetRequestWhenCalculationOperationWithHowMuchAndWithoutCountOfResourceFail(String incorrectRequest) {
        System.setIn(new ByteArrayInputStream(incorrectRequest.getBytes()));

        String result = new RequestRecipient().getRequest();

        assertNull(result);
    }
}
