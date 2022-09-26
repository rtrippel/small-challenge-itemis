import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Objects;
import java.util.Scanner;

public class RequestRecipient {

    public String getRequest() {
        final Scanner scanner = new Scanner(System.in);

        return verifyRequest(scanner.nextLine());
    }

    private String verifyRequest(String request) {
        if (Objects.equals(request, "/q")) return request;

        if (isRequestContainsIs(request) &&
                (isCalculationRequestCorrect(request)
                        || isDeterminingRequestCorrect(request)
                        || isInitializationRequestCorrect(request))) {
            return request;
        }

        return null;
    }

    private boolean isRequestContainsIs(String request) {
        return request.contains(" is ");
    }

    private boolean isCalculationRequestCorrect(String request) {
        final boolean isEndQuestionsMark = request.endsWith(" ?");
        final boolean isStartWithHowMuchOrManyCredits = request.startsWith("how much is ")
                || request.startsWith("how many Credits is ");
        final boolean isUnitsNames = request.split(" is ")[1].split(" ").length > 1;
        final String[] temp = request.split(" ");
        final boolean startWithHuwManyCredits = request.startsWith("how many Credits");
        final int MINIMUM_NUMBER_WORDS_IN_VALID_QUERY = 7;
        final boolean isResourceInUpperCase = temp.length > MINIMUM_NUMBER_WORDS_IN_VALID_QUERY
                && Character.isUpperCase(temp[temp.length - 2].charAt(0));

        return isEndQuestionsMark && isStartWithHowMuchOrManyCredits && isUnitsNames
                && (!startWithHuwManyCredits || isResourceInUpperCase);
    }

    private boolean isDeterminingRequestCorrect(String request) {
        final boolean isEndCredits = request.endsWith(" Credits");
        final String[] subStrings = request.split(" is ");
        final String[] firstPartWords = subStrings[0].split(" ");
        final boolean isLeftPartCorrect =  firstPartWords.length > 1
                && Character.isUpperCase(firstPartWords[firstPartWords.length -1].charAt(0));
        final String secondPart = subStrings[1];
        final String cost = secondPart.split(" ")[0];
        final boolean isNumber = NumberUtils.isCreatable(cost);
        final boolean isRightPartCorrect =  secondPart.split(" ").length == 2 && isEndCredits && isNumber;

        return isLeftPartCorrect && isRightPartCorrect;
    }

    private boolean isInitializationRequestCorrect(String request) {
        final boolean isOnlyTheeWords = request.split(" ").length == 3;
        final String romanNumeral = isOnlyTheeWords ? request.split(" is ")[1] : "";
        final boolean isOnEnumRomanNumeral =  EnumUtils.isValidEnum(RomanNumeral.class, romanNumeral);
        final boolean isCorrectRomanNumeral = !romanNumeral.isBlank() && isOnEnumRomanNumeral;
        final boolean isIntergalacticUnitsStartInLowerCase = Character.isLowerCase(request.charAt(0));

        return isOnlyTheeWords && isCorrectRomanNumeral && isIntergalacticUnitsStartInLowerCase;
    }
}
