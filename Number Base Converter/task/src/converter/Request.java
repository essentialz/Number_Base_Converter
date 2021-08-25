package converter;

import java.util.Optional;

public enum Request {
    CONTINUE(null),
    BACK("/back"),
    EXIT("/exit");

    private final String value;

    Request(String value) {
        this.value = value;
    }

    public static Optional<Request> getRequest(String request) {
        for (Request r: Request.values()) {
            if (request.equals(r.value)) {
                return Optional.of(r);
            }
        }

        return Optional.empty();
    }
}
