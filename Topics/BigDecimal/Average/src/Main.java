import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BigDecimal> numbers = scanner.useDelimiter("\n").tokens()
                .map(BigDecimal::new)
                .collect(Collectors.toList());

        System.out.println(numbers.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(numbers.size()), 0, RoundingMode.HALF_UP)
        );
    }
}