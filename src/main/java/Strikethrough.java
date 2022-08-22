import java.util.stream.Stream;

public class Strikethrough implements Text {
    private final Text text;

    Strikethrough(Text text) {
        this.text = text;
    }

    @Override
    public String getText() {
        return String.format("\u001B[9m%s\u001B[0m", text.getText());
    }

    @Override
    public int length() {
        return text.length() + 8;
    }

    @Override
    public Stream<Strikethrough> lines() {
        return text.lines().map(Strikethrough::new);
    }

    @Override
    public Text[] split(String regex) {
        return Stream.of(text.split(regex)).map(Strikethrough::new).toArray(Strikethrough[]::new);
    }

    @Override
    public Text strip() {
        return new Strikethrough(text.strip());
    }

    @Override
    public String toString() {
        return getText();
    }
}
