public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String title, String time) {
        this(title, time, time);
    }

    Event(String title, String startTime, String endTime) {
        super(title);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toSaveFormat() {
        return String.format("E;;%s;;%s;;%s", startTime, endTime, super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("📆 %s (from %s to %s)", super.toString(), startTime, endTime);
    }
}
