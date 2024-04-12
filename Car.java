public class Car extends Vehicle{ 
    String plateNumber;
   

    public Car(String plateNumber) {
        this.plateNumber = plateNumber;
        
    }

    public String getPlateNumber() {
        return plateNumber;
    }



    public String toString() {
        return "Plate Number: " + plateNumber +  "\n" + super.toString();
    }
}