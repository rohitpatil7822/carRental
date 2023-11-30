import java.util.*;

public class Car {

    private String carId;
    private String carModel;
    private String carBrand;
    private double carBasePrice;
    private boolean carIsAvailable;

//    parameterized Constructor ----------------------------------------------------------------------------------------

    Car(String carId, String carBrand, String carModel, double carBasePrice){
        this.carId = carId;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.carBasePrice = carBasePrice;
        this.carIsAvailable = true;
    }

//    getter Methods ----------------------------------------------------------------------------------------

    public String getCarId(){
        return this.carId;
    }

    public String getCarModel(){
        return this.carModel;
    }

    public String getCarBrand(){
        return this.carBrand;
    }

    public double getCarBasePrice(){

        return  this.carBasePrice;
    }

    public boolean getIsCarIsAvailable() {
        return this.carIsAvailable;
    }

    public double calculatePrice(int days) {
        return this.carBasePrice * days;
    }

//    setter Methods ----------------------------------------------------------------------------------------

    public void onRent() {
        this.carIsAvailable = false;
    }

    public void carReturn() {
        this.carIsAvailable = true;
    }
}
