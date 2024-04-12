import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        ParkingLot parkingLot = new ParkingLot();

        System.out.println("Enter the number of regular parking spots: ");
        int numRegularSpots = scanner.nextInt();

        System.out.print("Enter the number of compact parking spots: ");
        int numCompactSpots = scanner.nextInt();

        //add regular parking spots to the parking lot 

        for (int i = 0; i <= numRegularSpots; i++) {
            String spotId = "R" + i;
            ParkingSpot regularParkingSpot = new ParkingSpot(spotId);
            parkingLot.addParkingSpot(regularParkingSpot);

        } 

        //add compact parking spots to the parking lot

        for (int i = 0; i <= numCompactSpots; i++) {
            String spotId = "C" + i;
            ParkingSpot compactParkingSpot = new ParkingSpot(spotId, true);
            parkingLot.addParkingSpot(compactParkingSpot);
        }

        System.out.print("Enter the vehicle type (Car or Truck): ");
        String vehicleType = scanner.next();

        Vehicle vehicle; 
        if (vehicleType.equals("Car")){
            System.out.print("Enter the plate number: ");
            String plateNumber = scanner.next();
            vehicle = new Car(plateNumber);
        } else if (vehicleType.equals("Truck")) {
            System.out.print("Enter the plate number: ");
            String plateNumber = scanner.next();
            vehicle = new Truck(plateNumber);
        } else {
            System.out.println("Invalid vehicle type");
           return;
        }


        System.out.print("Enter the spot ID: ");
        String spotId = scanner.next(); 

        ParkingSpot selectedSpot = parkingLot.findParkingSpotById(spotId);
        if(selectedSpot != null && !selectedSpot.getIsOccupied()){
            if (vehicle instanceof Truck && selectedSpot.getIsCompact()) {
                System.out.println("Truck cannot be parked in a compact spot");
    
            } else {
                selectedSpot.addVehicleToRegularSpot(vehicle);
                System.out.println("Vehicle parked successfully at " + spotId);
            }
        } else {
            System.out.println("Invalid spot ID or Spot is occupied");
        }

        removeVehicleAndPrintBill(scanner, parkingLot);

        scanner.close();
    }
        


        public static void removeVehicleAndPrintBill (Scanner scanner, ParkingLot parkingLot){
            
            System.out.print("Enter the vehicle plate number to remove: ");
            String plateNumber = scanner.next();

            ArrayList<ParkingSpot> foundParkingSpots = parkingLot.findVehicleByPlateNumber(plateNumber);

            if (!foundParkingSpots.isEmpty()) {
                System.out.println("Found the vehicle with plate number " + plateNumber + " in the following spots:");
                for (ParkingSpot spot : foundParkingSpots) {
                    System.out.println(spot.getSpotId());
            }
            System.out.print("Enter the spot ID from which you want to remove the vehicle: ");
            String spotId = scanner.next();

            ParkingSpot spotToRemove = parkingLot.findParkingSpotById(spotId);

            if (spotToRemove != null && spotToRemove.getIsOccupied()) {
                Vehicle removedVehicle = spotToRemove.removeVehicle();
                if (removedVehicle != null) {
                    ChargingStrategy chargingStrategy;
                    if (removedVehicle instanceof Car) {
                        chargingStrategy = new ChargingStrategy(DayOfWeek.MONDAY); 
                    } else if (removedVehicle instanceof Truck) {
                        chargingStrategy = new ChargingStrategy(DayOfWeek.MONDAY); 
                    } else {
                        System.out.println("Invalid vehicle type");
                        return;
                    }

                    ParkingTicket ticket = new ParkingTicket(removedVehicle, spotToRemove);
                    Bill bill = new Bill(ticket, chargingStrategy);
                    System.out.println("Vehicle removed successfully.");
                    System.out.println(bill);
                } else {
                    System.out.println("Failed to remove vehicle.");
                }
            } else {
                System.out.println("Invalid spot ID or spot is not occupied");
            }
        } else {
            System.out.println("Vehicle with plate number " + plateNumber + " is not parked in any spot.");
        }

    }    
        
    }
    

