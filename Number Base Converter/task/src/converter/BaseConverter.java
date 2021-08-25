package converter;

import java.math.BigDecimal;
import java.time.format.DecimalStyle;

public class BaseConverter {
    private static final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final char SEPARATOR = DecimalStyle.ofDefaultLocale().getDecimalSeparator();
    protected static final int MIN_BASE = 2;
    protected static final int MAX_BASE = ALPHA_NUM.length();
    private static final int DECIMAL_BASE = 10;

    protected static String convert(String number, int sourceBase, int targetBase) {
        boolean isFractional = number.contains(String.valueOf(SEPARATOR));

        BigDecimal baseToDecimal = sourceBase == DECIMAL_BASE ? new BigDecimal(number)
                : baseToDecimal(number, sourceBase, isFractional);

        String decimalToBase;

        if (isFractional) {
            BigDecimal[] intAndRem = baseToDecimal.divideAndRemainder(BigDecimal.ONE);

            decimalToBase = integerToBase(intAndRem[0], targetBase)
                    .concat(SEPARATOR + remainderToBase(intAndRem[1], targetBase));
        } else {
            decimalToBase = integerToBase(baseToDecimal, targetBase);
        }

        return decimalToBase;
    }

    private static BigDecimal baseToDecimal(String number, int base, boolean isFractional) {
        String digits = number.replace(String.valueOf(SEPARATOR), "");
        int highestPower = isFractional ? number.indexOf(SEPARATOR) - 1
                : digits.length() - 1;

        BigDecimal conversion = BigDecimal.ZERO;

        for (int i = 0, exp = highestPower; i < digits.length(); i++, exp--) {
            int digitDecimalValue = ALPHA_NUM.indexOf(digits.toUpperCase().charAt(i));
            BigDecimal value = BigDecimal.valueOf(Math.pow(base, exp) * digitDecimalValue);

            conversion = conversion.add(value);
        }

        return conversion;
    }

    private static String integerToBase(BigDecimal  number, int base) {
        StringBuilder conversion = new StringBuilder();

        while (number.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal[] result = number.divideAndRemainder(BigDecimal.valueOf(base));
            conversion.append(ALPHA_NUM.charAt(result[1].intValue()));
            number = result[0];
        }

        return conversion.reverse().toString();
    }

    private static String remainderToBase(BigDecimal remainder, int base) {
        StringBuilder conversion = new StringBuilder();

        while (conversion.length() < 5) {
            if (remainder.compareTo(BigDecimal.ZERO) > 0) {
                BigDecimal bigBase = BigDecimal.valueOf(base);
                BigDecimal[] result = remainder.multiply(bigBase).divideAndRemainder(BigDecimal.ONE);
                conversion.append(ALPHA_NUM.charAt(result[0].intValue()));
                remainder = result[1];
            } else {
                conversion.append(BigDecimal.ZERO);
            }
        }

        return conversion.toString();
    }
}
