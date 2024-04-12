import java.util.ArrayList;

public class ParkingLot {

    private ArrayList<ParkingSpot> parkingSpots;

    public ParkingLot() {
        parkingSpots = new ArrayList<ParkingSpot>();
    }

    // addParkingSpot method

    public void addParkingSpot(ParkingSpot parkingSpot) {
        parkingSpots.add(parkingSpot);
    }

    // removeParkingSpot method

    public Boolean removeParkingSpot (String spotId) {
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getSpotId().equalsIgnoreCase(spotId) && !parkingSpot.getIsOccupied()) {
                parkingSpot.removeVehicle();
                return true;
            }
        }
        return false;
    }

    // get all available spots method

    public ArrayList<ParkingSpot> getAllAvailableSpots() {
        ArrayList<ParkingSpot> availableSpots = new ArrayList<ParkingSpot>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (!parkingSpot.getIsOccupied()) {
                availableSpots.add(parkingSpot);
            }
        }
        return availableSpots;
    }

    // find vehicle by plate number method

    public ArrayList<ParkingSpot> findVehicleByPlateNumber(String plateNumber) {
        ArrayList<ParkingSpot> foundParkingSpots = new ArrayList<ParkingSpot>();

        for (ParkingSpot parkingSpot : parkingSpots) {
            if(parkingSpot.getVehicle() instanceof Car ){
                if (((Car) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber) && parkingSpot.getIsOccupied()) {
                    foundParkingSpots.add(parkingSpot);
                }
            } else if(parkingSpot.getVehicle() instanceof Truck){
                if (((Truck) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber) && parkingSpot.getIsOccupied()) {
                    foundParkingSpots.add(parkingSpot);
                }
            }
        }
            return foundParkingSpots;
       
    }

    // find the parking spot by id method

    public ParkingSpot findParkingSpotById(String spotId) {
        ParkingSpot foundParkingSpot = new ParkingSpot("-1");
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getSpotId().equalsIgnoreCase(spotId)) {
                foundParkingSpot = parkingSpot;
                break;
            }
        }
        return foundParkingSpot;
    }

    // find the occupied spots method

    public ArrayList<ParkingSpot> getOccupiedSpots() {
        ArrayList<ParkingSpot> occupiedSpots = new ArrayList<ParkingSpot>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getIsOccupied()) {
                occupiedSpots.add(parkingSpot);
            }
        }
        return occupiedSpots;
    }

    // find the compact spots method

    public ArrayList<ParkingSpot> getCompactSpots() {
        ArrayList<ParkingSpot> compactSpots = new ArrayList<ParkingSpot>();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getIsCompact()) {
                compactSpots.add(parkingSpot);
            }
        }
        return compactSpots;
    }

    // find all spots method

    public ArrayList<ParkingSpot> getAllSpots() {
        return parkingSpots;
    }
    
}
