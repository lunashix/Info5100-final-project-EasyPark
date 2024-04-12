public class Truck extends Vehicle{
    String plateNumber;
   

    public Truck(String plateNumber) {
        this.plateNumber = plateNumber;
        
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public String toString() {
        return "Plate Number: " + plateNumber +  "\n" + super.toString();
    }
}
    

