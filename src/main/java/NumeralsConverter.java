/**
 * Roman to Arabic Converter Class
 */
public class NumeralsConverter {
    // Accepts a valid Roman number as input, returns an Arabic number
    public static int convertRomanToArabic(String romanNumber) {
        int result = 0;
        int subtractedNumber = 0;
        int current;
        int next;

        if (romanNumber.length() ==  1) return RomanNumeral.valueOf(romanNumber).getArabicValue();

        for (int i = 0; i < romanNumber.length() - 1; i++) {
            current = RomanNumeral.valueOf(String.valueOf(romanNumber.charAt(i))).getArabicValue();
            next = RomanNumeral.valueOf(String.valueOf(romanNumber.charAt(i + 1))).getArabicValue();
            if (next <= current) {
                result += current - subtractedNumber;
                subtractedNumber = 0;
            } else {
                subtractedNumber += current;
            }
            //At the last iteration, add the last Roman numeral
            if (i == romanNumber.length() - 2) {
                result += next - subtractedNumber;
            }
        }

        return result;
    }
}
