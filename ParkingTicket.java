import java.time.LocalDateTime;

public class ParkingTicket {
    private Vehicle parkedVehicle;
    private ParkingSpot parkingSpot;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
   

    public ParkingTicket(Vehicle parkedVehicle, ParkingSpot parkingSpot) {
        this.parkedVehicle = parkedVehicle;
        this.parkingSpot = parkingSpot;
        this.endTime = LocalDateTime.now();
        this.startTime = parkedVehicle.getArrivalTime();
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public long getDuration() {
        return parkedVehicle.getTimeIn();
    }

    public String toString() {
        return "Vehicle: " + parkedVehicle + "\nSpot: " + parkingSpot.getSpotId() + "\nStart Time: " + 
        startTime + "\nDuration: " + getDuration() + " minutes";
    }

  
    
    
}
