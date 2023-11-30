import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    RentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCars(Car car) {this.cars.add(car);}

    public void addCustomers(Customer customer) {this.customers.add(customer);}

    public void rentCar(Car car , Customer customer , int days){

        if (car.getIsCarIsAvailable()){
            car.onRent();
            rentals.add(new Rental(car ,customer , days));
        }else{
            System.out.println("Oops! car is not available for rent");
        }
    }

    public void returnCar(Car car){

        int removeFromRentals = 0;

        for (Rental rental: rentals) {

            if (rental.getCar() == car){
                rentals.remove(rental);
                removeFromRentals = 1;
                car.carReturn();
                break;
            }
        }

        if (removeFromRentals == 0) System.out.println("Car was not rented.");
    }

    public void menu(){

        Scanner scanner = new Scanner(System.in);

        while (true){

            System.out.println("===== Car Rental System =====");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1){

                System.out.println("\n== Rent a Car ==\n");
                System.out.print("Enter your name: ");
                String customerName = scanner.nextLine();

//              check and return available cars ===============================
                System.out.println("\nAvailable Cars:");

                for (Car car: cars) {

                    if (car.getIsCarIsAvailable()){
                        System.out.println(car.getCarId() + " - " + car.getCarBrand() + " " + car.getCarModel());
                    }
                }

                System.out.print("\nEnter the car ID you want to rent: ");

                String enteredCarId = scanner.nextLine();

                System.out.print("Enter the number of days for rental: ");

                int rentalDays = scanner.nextInt();
                scanner.nextLine();

//              Creating Customer Id and adding Customer Name ========================================================

                String customerID = "CUS" +customers.size() + 1;

                Customer newCustomer = new Customer(customerID,customerName);
                this.addCustomers(newCustomer);

//              check selected car is correct or not

                Car selectedCar = null;

                for (Car car:cars) {

                    if (car.getCarId().equals(enteredCarId) && car.getIsCarIsAvailable()){
                        selectedCar = car;
                        break;
                    }

                }

                if (selectedCar != null){

                    double totalPrice = selectedCar.calculatePrice(rentalDays);

                    System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getCustomerName());
                    System.out.println("Car: " + selectedCar.getCarBrand() + " " + selectedCar.getCarModel());
                    System.out.println("Rental Days: " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.print("\nConfirm rental (Y/N): ");
                    String confirm = scanner.nextLine();

                    if (confirm.equalsIgnoreCase("Y")){

                        rentCar(selectedCar , newCustomer , rentalDays);
                        System.out.println("\nCar rented successfully.");

                    }else {
                        System.out.println("\nRental canceled.");
                    }

                }else {
                    System.out.println("\nInvalid car selection or car not available for rent.");
                }

            } else if (choice == 2) {
                System.out.println("\n== Return a Car ==\n");
                System.out.print("Enter the car ID you want to return: ");

                String enteredReturnCarId = scanner.nextLine();

                Car carToReturn = null;

                for (Car car : cars) {

                    if (car.getCarId().equals(enteredReturnCarId) && !car.getIsCarIsAvailable()){

                        carToReturn = car;
                        break;
                    }

                }

                if (carToReturn != null) {

                    Customer rentalCustomer = null;

                    for (Rental rental : rentals){

                        if (rental.getCar() == carToReturn){

                            rentalCustomer = rental.getCustomer();
                            break;
                        }
                    }

                    if (rentalCustomer != null){
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + rentalCustomer .getCustomerName());
                    }else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else{
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            }else {
                System.out.println("Invalid Choice! Please enter a valid option.");
            }
        }

        System.out.println("Thank you for using the BookRide!");
    }
}
