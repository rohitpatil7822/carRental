public class Main {
    public static void main(String[] args) {

        RentalSystem rentalSystem = new RentalSystem();

        rentalSystem.addCars(new Car("C001", "Toyota", "Camry", 60.0));
        rentalSystem.addCars(new Car("C002", "Honda", "Accord", 70.0));
        rentalSystem.addCars(new Car("C003", "Mahindra", "Thar", 150.0));

        rentalSystem.menu();
    }
}
