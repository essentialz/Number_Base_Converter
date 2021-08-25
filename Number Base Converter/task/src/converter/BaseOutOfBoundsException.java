package converter;

public class BaseOutOfBoundsException extends RuntimeException{
    public BaseOutOfBoundsException(int base) {
        super(String.format("Base (%d) is out of bounds: Base should be between %d and %d inclusive.",
                base, BaseConverter.MIN_BASE, BaseConverter.MAX_BASE));
    }
}
