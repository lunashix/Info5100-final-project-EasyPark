import java.time.LocalDateTime;

public class ParkingTicket {
    private Vehicle parkedVehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime startTime;
   

    public ParkingTicket(Vehicle parkedVehicle, ParkingSpot parkingSpot) {
        this.parkedVehicle = parkedVehicle;
        this.parkingSpot = parkingSpot;
        this.startTime = LocalDateTime.now();
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public long getDuration() {
        return parkedVehicle.getTimeIn();
    }

    public String toString() {
        return "Vehicle: " + parkedVehicle + "\nSpot: " + parkingSpot.getSpotId() + "\nStart Time: " + 
        startTime + "\nDuration: " + getDuration() + " minutes";
    }

  
    
    
}
