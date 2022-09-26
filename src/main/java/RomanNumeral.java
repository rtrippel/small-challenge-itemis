/**
 * The Enum is designed to process the ratio of Arabic numerals - Roman numerals
 */
public enum RomanNumeral {
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);

    private final int arabicValue;

    RomanNumeral(int arabicValue) {
        this.arabicValue = arabicValue;
    }

    public int getArabicValue() {
        return arabicValue;
    }
}
