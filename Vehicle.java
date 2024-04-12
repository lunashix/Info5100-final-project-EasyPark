import java.time.LocalDateTime;
import java.time.LocalDate;

public abstract class Vehicle {
    private LocalDateTime arrivalTime;  // The time the vehicle arrived at the parking lot
    private LocalDate arrivalDate;

    public Vehicle() {
        arrivalTime = LocalDateTime.now();
        arrivalDate = LocalDate.now();
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public long getTimeIn() {
        return arrivalTime.until(LocalDateTime.now(), java.time.temporal.ChronoUnit.MINUTES);
    }

    public String toString() {
        return "Date:" + arrivalDate;
    }
}




