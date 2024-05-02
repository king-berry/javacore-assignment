package Customer;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        ManageCustomer manageCustomer = new ManageCustomer();
        manageCustomer.readDataFromFile();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("--------- Menu ---------");
            System.out.println("1. All Customers");
            System.out.println("2. Add customer");
            System.out.println("3. Search customer by phone number");
            System.out.println("4. Change customer's infomation");
            System.out.println("5. Delete customers");
            System.out.println("6. Exit");
            System.out.print("your choice: ");

                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        manageCustomer.displayAllCustomers();
                        break;
                    case "2":

                        String answer;
                        do{
                            System.out.print("New name: ");
                            String name = scanner.nextLine();
                            System.out.print("New email: ");
                            String email = scanner.nextLine();
                            System.out.print("New phone number: ");
                            String phoneNumber = scanner.nextLine();
                            Customer newCustomer = new Customer(name, email, phoneNumber);
                            manageCustomer.addCustomer(phoneNumber, newCustomer);
                            manageCustomer.saveDataToFile();
                            System.out.println("wanna add more (Y/N)?");
                            answer = scanner.nextLine();
                        }while(answer.equals("Y") || answer.equals("y"));
                        break;
                    case "3":
                        System.out.print("Phone number pls: ");
                        String searchPhoneNumber = scanner.nextLine();
                        manageCustomer.searchCustomerByPhoneNumber(searchPhoneNumber);
                        break;
                    case "4":
                        System.out.print("Phone number pls: ");
                        String editPhoneNumber = scanner.nextLine();
                        System.out.print("New name(Enter to skip): ");
                        String newName = scanner.nextLine();
                        System.out.print("New email (Enter to skip): ");
                        String newEmail = scanner.nextLine();
                        System.out.print("New phone number (Enter to skip): ");
                        String newPhoneNumber = scanner.nextLine();
                        manageCustomer.editCustomer(editPhoneNumber, newName, newEmail, newPhoneNumber);
                        manageCustomer.saveDataToFile();
                        break;
                    case "5":
                        System.out.print("Phone number pls: ");
                        String deletePhoneNumber = scanner.nextLine();
                        manageCustomer.deleteCustomer(deletePhoneNumber);
                        manageCustomer.saveDataToFile();
                        break;
                    case "6":
                        exit = true;
                        System.out.println("bye");
                        break;
                    default:
                        System.out.println("eyes ?");
                }
            }
        }
    }