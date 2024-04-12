import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Vehicle {
    private ParkingType vehicleType;
    private String plateNumber;
    private LocalDateTime arrivalTime; // The time the vehicle arrived at the parking lot
    private LocalDate arrivalDate;
    private LocalDateTime exitTime; // The time the vehicle arrived at the parking lot

    public LocalDateTime getExitTime() {
        return this.exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public ParkingType getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(ParkingType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Vehicle(ParkingType vehicleType, String plateNumber) {
        this.vehicleType = vehicleType;
        this.plateNumber = plateNumber;
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
