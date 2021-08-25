package converter;

import java.util.Scanner;

import static converter.MenuPrompt.*;
import static converter.Request.BACK;
import static converter.Request.EXIT;

public class Program {
    private static final Scanner SCANNER = new Scanner(System.in);

    private int sourceBase;
    private int targetBase;
    private String convertedNumber;

    public void run() {
        Request firstLevelRequest = getFirstLevelRequest();

        while (firstLevelRequest != EXIT) {
            Request secondLevelRequest = getSecondLevelRequest();

            while (secondLevelRequest != BACK) {
                print(String.format("%s%n%n", RESULT_MESSAGE.getPrompt(convertedNumber)));

                secondLevelRequest = getSecondLevelRequest();
            }

            print("\n");
            firstLevelRequest = getFirstLevelRequest();
        }
    }

    private Request getFirstLevelRequest() {
        print(FIRST_LEVEL_PROMPT.getPrompt());

        if (!SCANNER.hasNextInt()) {
            return getRequest(SCANNER.nextLine());
        }

        sourceBase = SCANNER.nextInt();
        checkBase(sourceBase);

        targetBase = SCANNER.nextInt();
        checkBase(targetBase);

        SCANNER.nextLine();

        return Request.CONTINUE;
    }

    private Request getSecondLevelRequest() {
        print(SECOND_LEVEL_PROMPT.getPrompt(sourceBase, targetBase));
        String input = SCANNER.nextLine();

        if (input.startsWith("/")) {
            return getRequest(input);
        }

        convertedNumber = BaseConverter.convert(input, sourceBase, targetBase);

        return Request.CONTINUE;
    }

    private Request getRequest(String request) {
        return Request.getRequest(request)
                .orElseThrow(() -> new RuntimeException("Invalid Input!"));
    }

    private void print(String message) {
        System.out.print(message);
    }

    private void checkBase(int base) {
        if (base < BaseConverter.MIN_BASE || base > BaseConverter.MAX_BASE) {
            throw new BaseOutOfBoundsException(base);
        }
    }
}
