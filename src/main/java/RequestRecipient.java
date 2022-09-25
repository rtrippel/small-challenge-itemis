import org.apache.commons.lang3.math.NumberUtils;

import java.util.Scanner;

public class RequestRecipient {

    public String getRequest() {
        final Scanner scanner = new Scanner(System.in);

        return verifyRequest(scanner.nextLine());
    }

    private String verifyRequest(String request) {

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
        boolean isEndQuestionsMark = request.endsWith(" ?");
        boolean isStartWithHowMuchOrManyCredits = request.startsWith("how much is ")
                || request.startsWith("how many Credits is ");

        return isEndQuestionsMark && isStartWithHowMuchOrManyCredits;
    }

    private boolean isDeterminingRequestCorrect(String request) {
        boolean isEndCredits = request.endsWith(" Credits");
        final String[] subStrings = request.split(" is ");
        final String[] firstPartWords = subStrings[0].split(" ");
        boolean isLeftPartCorrect =  firstPartWords.length > 1
                && Character.isUpperCase(firstPartWords[firstPartWords.length -1].charAt(0));
        final String secondPart = subStrings[1];
        final String cost = secondPart.split(" ")[0];
        boolean isNumber = NumberUtils.isCreatable(cost);
        boolean isRightPartCorrect =  secondPart.split(" ").length == 2 && isEndCredits && isNumber;
        return isLeftPartCorrect && isRightPartCorrect;
    }

    private boolean isInitializationRequestCorrect(String request) {
        return request.split(" ").length == 3;
    }
}
