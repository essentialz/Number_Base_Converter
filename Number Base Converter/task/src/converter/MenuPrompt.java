package converter;

public enum MenuPrompt {
    FIRST_LEVEL_PROMPT("Enter two numbers in format: {source base} {target base} (To quit type /exit) "),
    SECOND_LEVEL_PROMPT("Enter number in base %d to convert to base %d (To go back type /back) "),
    RESULT_MESSAGE("Conversion result: %s");

    private final String prompt;

    MenuPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getPrompt(String result) {
        return String.format(getPrompt(), result);
    }

    public String getPrompt(int sourceBase, int targetBase) {
        return String.format(getPrompt(), sourceBase, targetBase);
    }
}
