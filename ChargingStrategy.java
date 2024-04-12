import java.time.DayOfWeek;

public class ChargingStrategy {
    private DayOfWeek dayOfWeek;

    public ChargingStrategy(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }
  
    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public double calculateCharge(Vehicle vehicle) {
        double charge = 0;

        long hoursParked = vehicle.getTimeIn();

        if (vehicle instanceof Car) {
            charge = calculateCarCharge((Car) vehicle, hoursParked);

        } else if (vehicle instanceof Truck) {
            
            charge = calculateTruckCharge((Truck) vehicle, hoursParked);
        }
        return charge;
        
    }

    private double calculateCarCharge(Car car, long hoursParked) {
        double chargeHoursParked =  calculateChargePerHour();
        return hoursParked * chargeHoursParked;
    }

    private double calculateTruckCharge(Truck truck, long hoursParked) {
        double chargeHoursParked =  calculateChargePerHour();
        return hoursParked * chargeHoursParked * 1.5;
    }


    private double calculateChargePerHour() {
        double chargePerHour = 0;
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            chargePerHour = 2; 
        } else {
            chargePerHour = 5; 
        }
        return chargePerHour;
    }
}
