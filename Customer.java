import java.util.Scanner;
public class Customer {


    private String fName;

    private String sName;

    private int noOfBurgers;
    // Constructor for creating a customer from user input

    public Customer(Scanner input) {
        System.out.println("Enter the first name of the customer");
        fName = input.next();
        System.out.println("Enter the last name of the customer");
        sName = input.next();
        do{
            try{input.nextLine();
                System.out.print("No of Burgers: ");
                noOfBurgers = input.nextInt();
            }catch (Exception e){
                System.out.println("Invalid Input");
            }
        }while(noOfBurgers <= 0);
    }

    // Constructor for adding a customer with specified details
    public Customer(String fName, String sName, int noOfBurgers){
        this.fName = fName;
        this.sName = sName;
        this.noOfBurgers = noOfBurgers;
    }
    // Get the full name of the customer
    public String getFullName(){
        return fName + " " + sName;
    }
    // Get the number of burgers ordered by the customer

    public int getNoOfBurgers() {
        return noOfBurgers;
    }
    //constructor for getting customer details
    public String CustomerFullDetails(){ return fName + "-" + sName + "-" + noOfBurgers;}

    public String getfName() {
        return fName;
    }

    public String getsName() {
        return sName;
    }
}





