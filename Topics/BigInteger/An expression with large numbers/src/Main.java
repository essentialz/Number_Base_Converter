import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<BigInteger> numbers = scanner.useDelimiter(" ").tokens()
                .map(BigInteger::new)
                .collect(Collectors.toList());

        System.out.println(numbers.get(0).negate()
                        .multiply(numbers.get(1))
                        .add(numbers.get(2))
                        .subtract(numbers.get(3))
        );
    }
}