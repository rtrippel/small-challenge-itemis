public class NumeralsConverter {
    public static int convertRomanToArabic(String romanNumeral) {
        int result = 0;
        int temp = 0;
        int current;
        int next;

        for (int i = 0; i < romanNumeral.length() - 1; i++) {
            current = RomanNumeral.valueOf(String.valueOf(romanNumeral.charAt(i))).getArabicValue();
            next = RomanNumeral.valueOf(String.valueOf(romanNumeral.charAt(i + 1))).getArabicValue();
            if (next < current) {
                result += current + temp;
                temp = 0;
            } else {
                temp += current;
            }
        }
        //Adding the last Roman numeral
        result += RomanNumeral.valueOf(String.valueOf(romanNumeral.charAt(romanNumeral.length() - 1))).getArabicValue();

        return result;
    }
}
