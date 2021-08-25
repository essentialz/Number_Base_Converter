import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        BigDecimal startingAmount = scanner.nextBigDecimal();
        double interestRate = scanner.nextDouble();
        int numberOfYears = scanner.nextInt();

        System.out.printf("Amount of money in the account: %s",
                BigDecimal.ONE.add(BigDecimal.valueOf(interestRate / 100))
                        .pow(numberOfYears)
                        .multiply(startingAmount)
                        .setScale(2, RoundingMode.CEILING));
    }
}