package Customer;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ManageCustomer {
    public Map<String, Customer> customers = new HashMap();

//    public void SeedCustomer(){
//        Customer customer1 = new Customer("teo", "teo@gmail.com","0123456789");
//        Customer customer2 = new Customer("another teo", "teo2@gmail.com","0123456788");
//
//        customers.put("0123456789", customer1);
//        customers.put("0123456788", customer2);
//    }

    public void displayAllCustomers() {
        if(customers.size() > 0){
            for (Customer customer : customers.values()) {
                System.out.println("name: "+customer.getName() + "  Email: " + customer.getEmail() + "  PhoneNumber: " + customer.getPhoneNumber());
            }
        }
        else{
            System.out.println("don't have any customer");
        }
    }

    public void addCustomer(String phoneNumber, Customer customer) {
        if (customer.validate()) {
            if (!customers.containsKey(phoneNumber)) {
                customers.put(phoneNumber, customer);
                System.out.println("add success");
            } else {
                System.out.println("Phone number already exists");
            }
        } else {
            System.out.println("Invalid information");
        }
    }

    public void searchCustomerByPhoneNumber(String phoneNumber) {
            if (customers.containsKey(phoneNumber)) {
                Customer customer = customers.get(phoneNumber);
                System.out.println("Customer Information: " + customer.getName() + " - " + customer.getEmail() + " - " + customer.getPhoneNumber());
            } else {
                System.out.println("Customer does not exist");
            }

    }

    public void editCustomer(String phoneNumber, String newName, String newEmail, String newPhoneNumber) {
        if (customers.containsKey(phoneNumber)) {
            Customer customer = customers.get(phoneNumber);
            if (newName != null && !newName.isEmpty()) {
                customer.setName(newName);
            }
            if (newEmail != null && !newEmail.isEmpty()) {
                customer.setEmail(newEmail);
            }
            if (newPhoneNumber != null && !newPhoneNumber.isEmpty()) {
                if (customer.validate()) {
                    customers.remove(phoneNumber);
                    customer.setPhoneNumber(newPhoneNumber);
                    customers.put(newPhoneNumber, customer);
                    System.out.println("Information after change: " + customer.getName() + " - " + customer.getEmail() + " - " + customer.getPhoneNumber());
                } else {
                    System.out.println("Invalid information");
                }
            }
        } else {
            System.out.println("Customer does not exist");
        }
    }


    public void deleteCustomer(String phoneNumber) {
        if (customers.containsKey(phoneNumber)) {
            customers.remove(phoneNumber);
            System.out.println("Delete success");
        } else {
            System.out.println("Customer does not exist");
        }
    }

    // Phương thức để lưu thông tin khách hàng vào file
    public void saveDataToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Customer/CusInfo.txt"))) {
            for (Customer customer : customers.values()) {
                writer.write(customer.getName() + "," + customer.getEmail() + "," + customer.getPhoneNumber());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readDataFromFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader("src/Customer/CusInfo.txt"))){
            String line;
            while ((line = reader.readLine()) != null){
                String[] customerInfo = line.split(",");
                if (customerInfo.length == 3) {
                    Customer customer = new Customer(customerInfo[0], customerInfo[1], customerInfo[2]);
                    customers.put(customerInfo[2], customer);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
