import java.util.*;

public class Customer {

    private String customerId;
    private  String customerName;

    Customer(String Id , String Name){
        customerId = Id;
        customerName = Name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
}
