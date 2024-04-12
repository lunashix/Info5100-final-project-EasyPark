public class Car extends Vehicle {
    private String plateNumber;
    private ParkingType vehicleType;

    public Car(String plateNumber, ParkingType vehicleType) {
        super(vehicleType, plateNumber);
        this.plateNumber = plateNumber;
        this.vehicleType = ParkingType.REGULAR;
    }

    public String getPlateNumber() {
        return plateNumber;
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

    public String toString() {
        return "Plate Number: " + plateNumber + "\n" + super.toString();
    }
}