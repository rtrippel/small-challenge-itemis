import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class processes received commands, calculates data and displays messages.
 */
public class RequestHandler {

    private final IntergalacticUnit iUnit = IntergalacticUnit.getInstance();
    private final Resource resources = Resource.getInstance();

    public void execute(String operation) {
        final String UNKNOWN_RESPONSE = "I have no idea what you are talking about";
        if (operation == null) {
            System.out.println(UNKNOWN_RESPONSE);
            return;
        }

        final boolean isInitializationOperation = operation.split(" ").length == 3;
        final boolean isDeterminingOperation = operation.endsWith(" Credits");
        final boolean isCalculationOperation = operation.endsWith(" ?");

        if (isInitializationOperation) {
            final String[] keyValueArray = operation.split(" is ");
            final String key = keyValueArray[0];
            final String value = keyValueArray[1];

            iUnit.addConformity(key, value);
        } else if (isDeterminingOperation) {
            final String[] subStrings = operation.split(" is ");
            final String[] firstPartWords = subStrings[0].split(" ");
            final String resourceName = firstPartWords[firstPartWords.length -1];

            if (isUnknownNames(Arrays.copyOf(firstPartWords, firstPartWords.length -1))) return;
            final int resourceAmount = getArabicAmount(Arrays.copyOf(firstPartWords, firstPartWords.length -1));
            final String[] temp = operation.split(" ");
            final Double cost = Double.parseDouble(temp[temp.length - 2]) / resourceAmount;

            resources.addResourceNameAndCoast(resourceName, cost);
        } else if (isCalculationOperation) {
            if (operation.startsWith("how much is")) {
                final String iUnits = operation.replace("how much is ", "").replace(" ?", "");
                if (isUnknownNames(iUnits.split(" "))) return;
                final int arabicAmount = getArabicAmount(iUnits.split(" "));
                final String outMessage = String.format("%s is %d", iUnits, arabicAmount);

                System.out.println(outMessage);
            } else {
                final String secondPart =  operation.replace("how many Credits is ", "")
                        .replace(" ?", "");
                final String[] iUnitsAndResource = secondPart.split(" ");
                final String resource = iUnitsAndResource[iUnitsAndResource.length - 1];
                final double resourceCost = Resource.getInstance().getCost(resource);
                final int resourceAmount = getArabicAmount(Arrays.copyOf(iUnitsAndResource, iUnitsAndResource.length -1));
                DecimalFormat format = new DecimalFormat("0.#");
                final String outMessage
                        = String.format("%s is %s Credits", secondPart, format.format(resourceCost*resourceAmount));

                System.out.println(outMessage);
            }
        } else {
            System.out.println(UNKNOWN_RESPONSE);
        }
    }

    private int getArabicAmount(String[] iUnitAmount) {
        final StringBuilder romanNumber = new StringBuilder();
        for (String unit: iUnitAmount) {
            romanNumber.append(iUnit.getValue(unit));
        }

        return NumeralsConverter.convertRomanToArabic(romanNumber.toString());
    }

    private boolean isUnknownNames(String[] iUnitNames) {
        final List<String> unknownNames = getUnknownIUnitNames(iUnitNames);
        if (unknownNames.size() > 0) {
            System.out.println("Incorrect names entered, initialize them or re-enter the correct query.");
            System.out.println(unknownNames);
            return true;
        }
        return false;
    }

    private List<String> getUnknownIUnitNames(String[] iUnitNames) {
        final List<String> result = new ArrayList<>();
        for (String iUnitName: iUnitNames) {
             if(IntergalacticUnit.getInstance().getValue(iUnitName) == null) {
                 result.add(iUnitName);
             }
        }
        return result;
    }
}
