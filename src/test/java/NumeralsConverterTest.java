import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumeralsConverterTest {

    private static Stream<Arguments> provideRomanAndArabicNumerals() {

        return Stream.of(
                Arguments.of(2022, "MMXXII"),
                Arguments.of(888, "DCCCLXXXVIII"),
                Arguments.of(2006, "MMVI"),
                Arguments.of(1944, "MCMXLIV"),
                Arguments.of(1903, "MCMIII")
        );
    }

    @ParameterizedTest
    @MethodSource("provideRomanAndArabicNumerals")
    public void testRomanToArabicNumeralSuccess(int expected, String romanNumeral)  {
        int result = NumeralsConverter.convertRomanToArabic(romanNumeral);

        assertEquals(expected, result);
    }
}
