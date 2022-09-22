import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NumeralsConverterTest {

    @Test
    public void testMMVIToArabicSuccess() {
        String romanNumeral = "MMVI";
        int expected = 2006;

        int result = NumeralsConverter.convertRomanToArabic(romanNumeral);

        assertEquals(expected, result);
    }
}
