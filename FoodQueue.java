import java.io.FileWriter;
import java.io.IOException;

public class FoodQueue {
    Customer[] queue;
    public FoodQueue(int size){
        queue = new Customer[size];
    }
    // Check the element at the specified index in the queue and return its status

    public String checkQueueElement(int i) {
        if (i < 0 || i >= queue.length) {
            return " ";
        }

        if (queue[i] == null) {
            return "X";
        } else {
            return "0";
        }
    }
    // constructor to check if the queue is empty
    public boolean isEmpty(){
        return queue[0] == null;
    }
    //adding a customer to a cashier

    public void addingACustomer(Customer customer){
        for(int i=0; i< queue.length ; i++){
            if(queue[i] == null){
                queue[i] = customer;
                break;
            }
        }

    }
    // Check if the specified index in the queue is null
    public boolean indexChecker(int i){
        return queue[i] == null;
    }
    //getting the size of the queue
    public int queueSize(){
        return queue.length;
    }
    //setting the customer position at the specific index to null
    public void settingPositionToNull(int i){
         queue[i] = null;
    }
    // Move customers forward in the queue starting from the specified position
    public void customerMovesForward(int position){
        for(int i = position - 1; i < queue.length - 1; i++) {
            if (queue[i] == null && queue[i+1] != null) {
                queue[i] = queue[i+1];
                queue[i+1] = null;
            }
        }
    }
    // Check if the cashier queue is full
    public boolean checkFullCashier(){
        for (Customer customer : queue) {
            if (customer == null) {
                return false;
            }
        }
        return true;
    }
    // Print the name of the removed customer at the specified index
    public void gettingTheRemovedName(int i){
        System.out.println(queue[i].getFullName() + " has been removed");
    }
    // Get the customer at the specified index in the queue
    public Customer seeTheCustomer(int i){
        return queue[i];
    }
    // Get the length of the non-null elements in the queue
    public int getArrayLength(int index){
        for(Customer customer : queue){
            if(customer != null){
                index++;
            }
        }
        return index;
    }
    // Add customers to the array for sorting names in alphabetical order
    public int addCustomerToQueue(int index, String[] array){
        for(int i = 0; i < queue.length; i++){
            if(!indexChecker(i)){
                array[index] = queue[i].getFullName();
                index++;
            }
        }
        return index;
    }
    // Save the customers' details to a file
    public void saveTheCustomers(FileWriter dataFile) throws IOException {
        for(Customer customer : queue){
            if(customer == null){
                dataFile.write("** \n");
            }else{
                dataFile.write(customer.CustomerFullDetails() +"\n");
            }
        }
    }
    // Set the customer details at the specified index in the queue
    public void setCustomerDetails(int i, String fName, String sName, String burgers){
        int noOfBurgers = Integer.parseInt(burgers);
        Customer customer = new Customer(fName, sName, noOfBurgers);
        queue[i] = customer;
    }
    //method of checking income of specific cashier and this will be called in the main
    public int checkingIncome() {
        int income = 0;
        for (Customer customer : queue) {
            if (customer != null) {
                int numberOfBurgers = customer.getNoOfBurgers();
                int burgerPrice = 650;
                income += numberOfBurgers * burgerPrice;
            }
        }
        return income;
    }





}
