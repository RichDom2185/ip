import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDate byDate;
    private LocalTime byTime;

    Deadline(String title, String deadline) throws IllegalArgumentException {
        this(title, false, deadline);
    }

    Deadline(String title, boolean isCompleted, String deadline) throws IllegalArgumentException {
        super(title, isCompleted);
        byDate = RegexHelper.extractAndParseDate(deadline)
                .orElseThrow(() -> new IllegalArgumentException("Invalid deadline date: " + deadline));
        byTime = RegexHelper.extractAndParseTime(deadline)
                .orElseThrow(() -> new IllegalArgumentException("Invalid deadline time: " + deadline));
    }

    public static Deadline fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 3) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Deadline(args[2], args[1].equals("1"), args[0]);
    }

    @Override
    public String toSaveFormat() {
        return String.format("D;;%s;;%s", deadline, super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("‼ %s (by %s at %s)", super.toString(), byDate, byTime);
    }
}
