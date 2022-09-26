public class NumeralsConverter {
    public static int convertRomanToArabic(String romanNumeral) {
        int result = 0;
        int subtractedNumber = 0;
        int current;
        int next;

        if (romanNumeral.length() ==  1) return RomanNumeral.valueOf(romanNumeral).getArabicValue();

        for (int i = 0; i < romanNumeral.length() - 1; i++) {
            current = RomanNumeral.valueOf(String.valueOf(romanNumeral.charAt(i))).getArabicValue();
            next = RomanNumeral.valueOf(String.valueOf(romanNumeral.charAt(i + 1))).getArabicValue();
            if (next <= current) {
                result += current - subtractedNumber;
                subtractedNumber = 0;
            } else {
                subtractedNumber += current;
            }
            //At the last iteration, add the last Roman numeral
            if (i == romanNumeral.length() - 2) {
                result += next - subtractedNumber;
            }
        }

        return result;
    }
}
