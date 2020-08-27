package main.java.tasks;
import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event, consisting of a description and a time.
 * Throws InvalidDescriptionException or InvalidTimeException if
 * the description or time provided is blank.
 */
public class Event extends Task {

    private final String time;
    private LocalDateTime formattedDateTime;
    private LocalDate formattedDate;

    public Event(String description, String time) throws InvalidDescriptionException, InvalidTimeException {
        super(description);
        if (description.isBlank()) {
            throw new InvalidDescriptionException(
                    "Hey! Event description shouldn't be blank.");
        } else if (time.isBlank()) {
            throw new InvalidTimeException(
                    "Do try again by adding a time where the event takes place.");
        } else {
            this.time = time;
            formatTime();
        }
    }

    public Event(String description, String time, boolean isDone) {
        super(description, isDone);
        this.time = time;
        formatTime();
    }

    private void formatTime() {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            this.formattedDateTime = LocalDateTime.parse(this.time, formatter);
        } catch (DateTimeParseException e1) {
            this.formattedDateTime = null;
            try {
                this.formattedDate = LocalDate.parse(this.time);
            } catch (DateTimeParseException e2) {
                this.formattedDate = null;
            }
        }
    }

    /**
     * Returns the string that represents the event.
     *
     * @return the string consisting of the tag,
     * done status, description and time
     */
    @Override
    public String toString() {
        return this.formattedDateTime == null
                ? this.formattedDate == null
                    ? "[E]" + super.toString() + " (at: " + this.time + ")"
                    : "[E]" + super.toString() + " (at: " +
                        this.formattedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")"
                : "[E]" + super.toString() + " (at: " +
                    this.formattedDateTime.format(
                            DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a")) + ")";
    }

    /**
     * Returns the string that represents the event in a database.
     *
     * @return the string consisting of the tag,
     * done status, description and time
     */
    @Override
    public String databaseString() {
        return "E | " + super.databaseString() + " | " + this.time;
    }
}
