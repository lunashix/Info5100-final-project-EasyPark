import java.time.LocalDateTime;

public class Bill {
    private ParkingTicket ticket;
    private LocalDateTime endTime;
    private double totalCharge;
    private ChargingStrategy chargingStrategy;
 
  

    public Bill(ParkingTicket ticket, ChargingStrategy chargingStrategy) {
        this.ticket = ticket;
        this.chargingStrategy = chargingStrategy;
        this.endTime = LocalDateTime.now();
        calculateTotalCharge();
        
    }
    
    public void calculateTotalCharge() {
        Vehicle vehicle = ticket.getParkedVehicle();
        if (vehicle instanceof Car) {
            totalCharge = chargingStrategy.calculateCharge(ticket.getParkedVehicle());
        } else if (vehicle instanceof Truck) {
            totalCharge = chargingStrategy.calculateCharge(ticket.getParkedVehicle());
        }
    }

    public ParkingTicket getTicket() {
        return ticket;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public String  toString () {
        return "Parking Receipt:\n" +
                "Vehicle: " + ticket.getParkedVehicle() + "\n" +
                "Spot: " + ticket.getParkingSpot().getSpotId() + "\n" +
                "From: " + ticket.getStartTime() + "\n" +
                "To: " + endTime + "\n" +
                "Duration: " + ticket.getDuration() + " minutes\n" +
                "Parking Fee: $" + totalCharge + "\n" +
                "Thank you and Drive Safely!";
    }
    
}
