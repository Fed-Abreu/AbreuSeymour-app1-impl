package baseline;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Task {
    //Initialize private description, completed, and date
    private final SimpleStringProperty description;
    private final BooleanProperty completed;
    private final SimpleObjectProperty<LocalDate> deadlineDate;

    //Make a Constructor for date completed date
    Task(String description1, LocalDate date) {
        this.description = new SimpleStringProperty(description1);
        this.completed = new SimpleBooleanProperty(false);
        this.deadlineDate = new SimpleObjectProperty<>(date);
    }

    //Create getDescription method and return and return description
    public String getDescription() {
        return description.get();
    }
    //Set Description
    public void setDescription(String deadline1) {
        description.set(deadline1);
    }
    //Create getCompleted return completed
    public BooleanProperty getCompleted() {
        return completed;
    }
    //Set Completed
    public void setCompleted(boolean completed1) {
        completed.set(completed1);
    }
    //Create LocalDate getDate return Date
    public LocalDate getDeadlineDate() {
        return deadlineDate.get();
    }
    //Create getDeadline return Deadline
    public String getDeadline() {
        return deadlineDate.get().toString();
    }
    //Set DeadlineDate
    public void setDeadlineDate(LocalDate date) {
        deadlineDate.set(date);
    }
}
