package mia.tasks;

import general.utils.RegexHelper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Event extends Task {
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;

    Event(String title, boolean isCompleted, String time) {
        this(title, isCompleted, time, time);
    }

    Event(String title, String time) {
        this(title, false, time, time);
    }

    Event(String title, boolean isCompleted, String start, String end) throws IllegalArgumentException {
        super(title, isCompleted);
        startDate = RegexHelper.extractAndParseDate(start)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event start date: " + start));
        startTime = RegexHelper.extractAndParseTime(start)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event start time: " + start));
        endDate = RegexHelper.extractAndParseDate(end)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event end date: " + end));
        endTime = RegexHelper.extractAndParseTime(end)
                .orElseThrow(() -> new IllegalArgumentException("Invalid event end time: " + end));
    }

    public static Event fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Event(args[3], args[2].equals("1"), args[0], args[1]);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E;;%s %s;;%s %s;;%s",
                startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
                startTime,
                endDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)),
                endTime, super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("📆 %s (from %s %s to %s %s)", super.toString(), startDate, startTime, endDate, endTime);
    }
}