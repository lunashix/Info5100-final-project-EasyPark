import java.time.LocalDateTime;

public class ParkingSpot {
    private ParkingType spotType;

    private String spotId;
    private Vehicle parkedVehicle;
    private boolean isOccupied;
    private boolean isCompact;
    private LocalDateTime arrivalTime;

    public ParkingType getSpotType() {
        return this.spotType;
    }

    public void setSpotType(ParkingType spotType) {
        this.spotType = spotType;
    }

    public void setSpotId(String spotId) {
        this.spotId = spotId;
    }

    public Vehicle getParkedVehicle() {
        return this.parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    // Constructor for Compact Parking Spot

    public ParkingSpot(String spotId, ParkingType spotType) {
        // this.spotType = "Compact";
        this.spotId = spotId;
        this.spotType = spotType;
        this.isOccupied = false;
        // this.isCompact = isCompact;
    }

    // Constructor for Regular Parking Spot

    // public ParkingSpot(String spotId) {
    // this.spotType = "Regular";
    // this.spotId = spotId;
    // this.isOccupied = false;
    // this.isCompact = false;
    // }

    // addVehicleToCompactSpot method
    public void addVehicleToRegularSpot(Vehicle parkedVehicle) {
        if (isOccupied) {
            System.out.println("Spot is already occupied");
        } else {
            this.parkedVehicle = parkedVehicle;
            isOccupied = true;
            isCompact = false;
            arrivalTime = LocalDateTime.now();
        }
    }

    // addVehicleToRegularSpot method
    public void addVehicleToCompactSpot(Vehicle parkedVehicle) {
        if (isOccupied) {
            System.out.println("Spot is already occupied");
        } else {
            this.parkedVehicle = parkedVehicle;
            isOccupied = true;
            isCompact = true;
            arrivalTime = LocalDateTime.now();
        }
    }

    // get spotId method

    public String getSpotId() {
        return spotId;
    }

    // removeVehicle method

    public Vehicle removeVehicle() {
        if (isOccupied) {
            isOccupied = false;
            return parkedVehicle;
        } else {
            return null;
        }
    }

    // getVehicle method

    public Vehicle getVehicle() {
        return parkedVehicle;
    }

    // get isOccupied method

    public boolean getIsOccupied() {
        return isOccupied;
    }

    // get isCompact method

    public boolean getIsCompact() {
        return isCompact;
    }

    // setter for isCompact

    public void setIsCompact(boolean isCompact) {
        this.isCompact = isCompact;
    }

    // get arrivalTime method

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    // toString method

    public String toString() {
        if (parkedVehicle != null) {
            if (parkedVehicle.getClass().isInstance(Car.class)) {
                return "Spot ID: " + spotId + "\nSpot Type: " + spotType + "\n Occupied: " + (isOccupied ? "Yes" : "No")
                        + "\nIs Compact: " + isCompact + "\n" + ((Car) parkedVehicle).getPlateNumber();
            } else if (parkedVehicle.getClass().isInstance(Truck.class)) {
                return "Spot ID: " + spotId + "\nSpot Type: " + spotType + "\n Occupied: " + (isOccupied ? "Yes" : "No")
                        + "\nIs Compact: " + isCompact + "\n" + ((Truck) parkedVehicle).getPlateNumber();
            }
        }

        return "Spot ID: " + spotId + "\nSpot Type: " + spotType + "\n Occupied: " + (isOccupied ? "Yes" : "No")
                + "\nIs Compact: " + isCompact;

    }

}
