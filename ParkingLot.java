import java.time.LocalDateTime;
import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<ParkingSpot> regularSpotList;

    private ArrayList<ParkingSpot> oversizedSpotList;
    private ArrayList<ParkingSpot> parkingSpots;

    public ParkingLot() {
        this.regularSpotList = new ArrayList<>();
        this.oversizedSpotList = new ArrayList<>();
        this.parkingSpots = new ArrayList<ParkingSpot>();
        parkingSpots.addAll(oversizedSpotList);
        parkingSpots.addAll(regularSpotList);
    }

    public ParkingLot(ArrayList<ParkingSpot> regularSpotList,
            ArrayList<ParkingSpot> oversizedSpotList) {
        this.regularSpotList = regularSpotList;
        this.oversizedSpotList = oversizedSpotList;
    }

    public ArrayList<ParkingSpot> getRegularSpotList() {
        return this.regularSpotList;
    }

    public void setRegularSpotList(ArrayList<ParkingSpot> regularSpotList) {
        this.regularSpotList = regularSpotList;
    }

    public ArrayList<ParkingSpot> getOversizedSpotList() {
        return this.oversizedSpotList;
    }

    public void setOversizedSpotList(ArrayList<ParkingSpot> oversizedSpotList) {
        this.oversizedSpotList = oversizedSpotList;
    }

    public void parkVehicle(Vehicle parkedVehicle) {
        for (ParkingSpot spot : getAllAvailableSpots()) {
            if (parkedVehicle.getVehicleType() == spot.getSpotType()) {
                spot.setIsOccupied(true);
                spot.setParkedVehicle(parkedVehicle);
                parkedVehicle.setArrivalTime(LocalDateTime.now());
                return;
            }
        }
    }

    public ParkingTicket removeVehicleByPlateNumber(String plateNumber) {
        ParkingSpot occupidSpot = findVehicleByPlateNumber(plateNumber);
        Vehicle parkedVehicle = occupidSpot.getParkedVehicle();
        ParkingTicket ticket = new ParkingTicket(parkedVehicle, occupidSpot);
        occupidSpot.setIsOccupied(false);
        occupidSpot.setParkedVehicle(null);
        return ticket;
    }

    public ParkingTicket removeVehicleBySpotId(String spotId) {
        ParkingTicket ticket = null;
        for (ParkingSpot spot : getOccupiedSpots()) {
            if (spot.getSpotId() == spotId) {
                Vehicle parkedVehicle = spot.getParkedVehicle();
                ticket = new ParkingTicket(parkedVehicle, spot);
                spot.setIsOccupied(false);
                spot.setParkedVehicle(null);
                break;
            }
        }
        return ticket;
    }

    // addParkingSpot method

    public void addParkingSpot(ParkingSpot parkingSpot) {
        if (parkingSpot.getSpotType() == ParkingType.REGULAR) {
            regularSpotList.add(parkingSpot);
        } else {
            oversizedSpotList.add(parkingSpot);
        }
        parkingSpots.add(parkingSpot);
    }

    // removeParkingSpot method

    public Boolean removeParkingSpot(String spotId) {
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
    public ParkingSpot findVehicleByPlateNumber(String plateNumber) {
        // ArrayList<ParkingSpot> foundParkingSpots = new ArrayList<ParkingSpot>();

        for (ParkingSpot parkingSpot : getOccupiedSpots()) {
            if (parkingSpot.getParkedVehicle().getPlateNumber().equals(plateNumber)) {
                return parkingSpot;
            }
        }
        return null;
    }

    // if (parkingSpot.getVehicle() instanceof Car) {
    // if (((Car) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber)
    // && parkingSpot.getIsOccupied()) {
    // foundParkingSpots.add(parkingSpot);
    // }
    // } else if (parkingSpot.getVehicle() instanceof Truck) {
    // if (((Truck) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber)
    // && parkingSpot.getIsOccupied()) {
    // foundParkingSpots.add(parkingSpot);
    // }
    // }
    // }
    // return foundParkingSpots;

    // }
    // public ArrayList<ParkingSpot> findVehicleByPlateNumber(String plateNumber) {
    // ArrayList<ParkingSpot> foundParkingSpots = new ArrayList<ParkingSpot>();

    // for (ParkingSpot parkingSpot : parkingSpots) {
    // if (parkingSpot.getVehicle() instanceof Car) {
    // if (((Car) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber)
    // && parkingSpot.getIsOccupied()) {
    // foundParkingSpots.add(parkingSpot);
    // }
    // } else if (parkingSpot.getVehicle() instanceof Truck) {
    // if (((Truck) parkingSpot.getVehicle()).getPlateNumber().equals(plateNumber)
    // && parkingSpot.getIsOccupied()) {
    // foundParkingSpots.add(parkingSpot);
    // }
    // }
    // }
    // return foundParkingSpots;

    // }

    // find the parking spot by id method

    // public ParkingSpot findParkingSpotById(String spotId) {
    // ParkingSpot foundParkingSpot = new ParkingSpot("-1");
    // for (ParkingSpot parkingSpot : parkingSpots) {
    // if (parkingSpot.getSpotId().equalsIgnoreCase(spotId)) {
    // foundParkingSpot = parkingSpot;
    // break;
    // }
    // }
    // return foundParkingSpot;
    // }

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

    // public ArrayList<ParkingSpot> getCompactSpots() {
    // ArrayList<ParkingSpot> compactSpots = new ArrayList<ParkingSpot>();
    // for (ParkingSpot parkingSpot : parkingSpots) {
    // if (parkingSpot.getIsCompact()) {
    // compactSpots.add(parkingSpot);
    // }
    // }
    // return compactSpots;
    // }

    // find all spots method

    public ArrayList<ParkingSpot> getAllSpots() {
        return parkingSpots;
    }

}
