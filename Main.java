
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;




public class Main {
    public static int burgers = 50;
    public static void main(String[] args) {
        FoodQueue cashierI = new FoodQueue(2);
        FoodQueue cashierII = new FoodQueue(3);
        FoodQueue cashierIII = new FoodQueue(5);
        FoodQueue waitingQueue = new FoodQueue(50);
        Scanner input = new Scanner(System.in);



        //menu options


        System.out.println("""
                Welcome to Foodies Fave Food center.
                100 or VFQ: View all Queues.
                101 or VEQ: View all Empty Queues.
                102 or ACQ: Add customer to a Queue.
                103 or RCQ: Remove a customer from a Queue. (From a specific location)
                104 or PCQ: Remove a served customer.
                105 or VCS: View Customers Sorted in alphabetical order 
                106 or SPD: Store Program Data into file.
                107 or LPD: Load Program Data from file.
                108 or STK: View Remaining burgers Stock.
                109 or AFS: Add burgers to Stock.
                110 or IFQ: Print Income Details
                999 or EXT: Exit the Program.
                price of a burger = Rs 650/=
                """);
        //while loop for the main program

        while (true) {
            int Intcommand = 0;
            String Stringcommand = "";
            Scanner scanner = new Scanner(System.in);
            System.out.println("please enter the command-");
            //identifies user inputs as integers or strings

            if (scanner.hasNextInt()) {
                int userInt = Integer.parseInt(scanner.nextLine());
                Intcommand += userInt;
            } else {
                String userString = scanner.nextLine();
                Stringcommand += userString;
            }
            //responses performed based on the user inputs


            if (Intcommand == 100 || Stringcommand.equalsIgnoreCase("vfq")) {
                cashiers(cashierI, cashierII, cashierIII);

            } else if (Intcommand == 101 || Stringcommand.equalsIgnoreCase("veq")) {
                emptyQueue(cashierI, cashierII, cashierIII);

            }else if (Intcommand == 102 || Stringcommand.equalsIgnoreCase("acq")) {
                shortestCashier(cashierI, cashierII, cashierIII, waitingQueue, input);

            }else if(Intcommand == 103 || Stringcommand.equalsIgnoreCase("rcq")){
                customerRemoval(cashierI, cashierII, cashierIII, input, waitingQueue);


            }else if (Intcommand == 104 || Stringcommand.equalsIgnoreCase("pcq")) {
                customerServer(cashierI, cashierII, cashierIII, input, waitingQueue);

            }else if (Intcommand == 105 || Stringcommand.equalsIgnoreCase("vcs")) {
                sortNames(cashierI, cashierII, cashierIII);


            } else if (Intcommand == 106 || Stringcommand.equalsIgnoreCase("spd")) {
                storingData(cashierI, cashierII, cashierIII, waitingQueue);


            } else if (Intcommand == 107 || Stringcommand.equalsIgnoreCase("lpd")) {
                loadingData(cashierI, cashierII, cashierIII, waitingQueue);


            }else if(Intcommand == 108 || Stringcommand.equalsIgnoreCase("stk")) {
                viewingTheRemainingBurgers();


            }else if (Intcommand == 109 || Stringcommand.equalsIgnoreCase("afs")) {
                addingBurgers(input);

            }else if (Intcommand == 110 || Stringcommand.equalsIgnoreCase("IFQ")) {
                printingIncomeDetails(cashierI, cashierII, cashierIII);
            }else if(Intcommand == 999 || Stringcommand.equalsIgnoreCase("ext")){
                System.out.println("Exit the program");
                System.out.println("Thank you for consuming our products");
                break;
            }else {
                System.out.println("Input is invalid");
            }
            if( burgers <= 10 ){
                System.out.println();
                System.out.println("warning");
                System.out.printf("Only %d burgers are remaining",burgers);
            }
        }

    }
    // Method to display the state of the cashiers
    public static void cashiers(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII){
        System.out.println("""
                ***********
                * Cashier *
                ***********""");
        for(int i = 0; i < 5; i++){
            System.out.println("  " + cashierI.checkQueueElement(i) + "  " + cashierII.checkQueueElement(i) + "  " + cashierIII.checkQueueElement(i));
        }
        System.out.println("0 = Occupied  X = Empty");
    }
    // Method to check and display if any cashier is empty
    public static void emptyQueue(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII) {
        if(cashierI.isEmpty() && cashierII.isEmpty() && cashierIII.isEmpty()){
            System.out.println("All cashiers are empty");
        }else{
            if(cashierI.isEmpty()){
                System.out.println("cashier one is empty");
            }
            if(cashierII.isEmpty()){
                System.out.println("cashier two is empty");
            }
            if(cashierIII.isEmpty()){
                System.out.println("cashier three is empty");
            }
        }
    }
    // Method to add a customer to the shortest cashier queue
    public static void shortestCashier(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII, FoodQueue waitingQueue, Scanner input) {
        Customer customer = new Customer(input);
        if(cashierI.checkFullCashier() && cashierII.checkFullCashier() && cashierIII.checkFullCashier()){
            if(waitingQueue.checkFullCashier()){
                System.out.println("All the cashiers are full ");
                System.out.println("Waiting queue" +
                        " is also full, sorry,Cannot add the customer");
            }else {
                waitingQueue.addingACustomer(customer);
                System.out.println(customer.getFullName() + " added to the waiting queue");
            }

        } else {
            for (int i = 0; i < 5; i++) {
                if (i == 0 || i == 1) {
                    if (cashierI.indexChecker(i)) {
                        cashierI.addingACustomer(customer);
                        System.out.println(customer.getFullName() + " added to cashier I");
                        break;
                    }
                }
                if (i == 0 || i == 1 || i == 2) {
                    if (cashierII.indexChecker(i)) {
                        cashierII.addingACustomer(customer);
                        System.out.println(customer.getFullName() + " added to cashier II");
                        break;
                    }
                }
                if (cashierIII.indexChecker(i)) {
                    cashierIII.addingACustomer(customer);
                    System.out.println(customer.getFullName() + " added to cashier III");
                    break;
                }

            }
        }
    }
    // Method to remove a customer from a specific cashier queue
    public static void customerRemoval(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII, Scanner input, FoodQueue waitingQueue) {
        try {
            System.out.print("From what cashier would you like to remove a customer: ");
            int queue = input.nextInt();
            System.out.print("Enter position you prefer: ");
            int position = input.nextInt();

            switch (queue) {
                case 1 -> servedCustomerRemovalFromCashier(cashierI, position, waitingQueue);
                case 2 -> servedCustomerRemovalFromCashier(cashierII, position, waitingQueue);
                case 3 -> servedCustomerRemovalFromCashier(cashierIII, position, waitingQueue);
                default -> System.out.println("Invalid queue");
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }
    //Method to remove a served customer from a cashier
    public  static void servedCustomerRemovalFromCashier(FoodQueue queue, int position, FoodQueue waitingQueue){
        if(queue.indexChecker(position - 1)){
            System.out.println("position is already empty");
        }else if(queue.queueSize() < position){
            System.out.println("Invalid input");
        }else{
            queue.gettingTheRemovedName(position - 1);
            queue.settingPositionToNull(position - 1);
            queue.customerMovesForward(position);
            addingTheCustomerFromWaitingQueue(queue, waitingQueue);
        }
    }

    public static void customerServer(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII, Scanner input, FoodQueue waitingQueue) {
        try {
            System.out.print("What cashier would you like to serve: ");
            int queue = input.nextInt();
            switch (queue) {
                case 1 -> removeTheServedCustomer(cashierI, waitingQueue);
                case 2 -> removeTheServedCustomer(cashierII, waitingQueue);
                case 3 -> removeTheServedCustomer(cashierIII, waitingQueue);
                default -> System.out.println("Invalid input");
            }
        }catch (Exception e) {
            System.out.println("Invalid Input");
        }
    }
    public static void addingTheCustomerFromWaitingQueue(FoodQueue queue, FoodQueue waitingQueue){
        if(!waitingQueue.isEmpty()){
            queue.setCustomerDetails(queue.queueSize() - 1, waitingQueue.seeTheCustomer(0).getfName(), waitingQueue.seeTheCustomer(0).getsName(), Integer.toString(waitingQueue.seeTheCustomer(0).getNoOfBurgers()));
            System.out.println(waitingQueue.seeTheCustomer(0).getFullName() + " added to the queue from the waiting queue");
            waitingQueue.settingPositionToNull(0);
            waitingQueue.customerMovesForward(1);
        }
    }
    public static void removeTheServedCustomer(FoodQueue queue, FoodQueue waitingQueue){
        if(queue.indexChecker(0)){
            System.out.println("No one is there to serve");
        }else{
            if(burgers < queue.seeTheCustomer(0).getNoOfBurgers()){
                System.out.println("Don't have that much burgers in stock");
            }else{
                burgers = burgers - queue.seeTheCustomer(0).getNoOfBurgers();
                System.out.println(queue.seeTheCustomer(0).getFullName() + " Was served " + queue.seeTheCustomer(0).getNoOfBurgers() + " burgers");
                servedCustomerRemovalFromCashier(queue, 1, waitingQueue);
            }
        }
    }
    public static void sortNames(FoodQueue CashierI, FoodQueue CashierII, FoodQueue CashierIII){
        int totalLength = 0;

        totalLength = CashierI.getArrayLength(totalLength);
        totalLength = CashierII.getArrayLength(totalLength);
        CashierIII.getArrayLength(totalLength);

        String[] combinedArray = new String[totalLength];

        int index = 0;

        index = CashierI.addCustomerToQueue(index, combinedArray);
        index = CashierII.addCustomerToQueue(index, combinedArray);
        CashierIII.addCustomerToQueue(index, combinedArray);

        viewCustomersSorted(combinedArray);
    }


    //This is represents a how to sort customer names in Alphabetical order.
    public static void viewCustomersSorted(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (customerRelationTo(array[i], array[j]) > 0) {
                    String temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        for (String name : array) {
            System.out.println("- " + name);
        }
    }


    public static int customerRelationTo(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int minLength = Math.min(len1, len2);

        for (int i = 0; i < minLength; i++) {
            char ch1 = str1.charAt(i);
            char ch2 = str2.charAt(i);

            if (ch1 != ch2) {
                return ch1 - ch2;
            }
        }
        return len1 - len2;
    }
    //Method to store data in a file
    public static void storingData(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII, FoodQueue waitingQueue){
        try{
            FileWriter dataFile = new FileWriter("dataFile.txt");
            dataFile.write(burgers+"\n");
            cashierI.saveTheCustomers(dataFile);
            cashierII.saveTheCustomers(dataFile);
            cashierIII.saveTheCustomers(dataFile);
            waitingQueue.saveTheCustomers(dataFile);
            dataFile.close();
            System.out.print("Data is successfully saved");
        }catch(Exception e){
            System.out.println("Error occurred");
        }
    }
    //Method to load data saved in the data file
    public static void loadingData(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII, FoodQueue waitingQueue){
        try {
            File dataFile = new File("dataFile.txt");
            Scanner fileScanner = new Scanner(dataFile);
            burgers = Integer.parseInt(fileScanner.nextLine());
            loadingCustomer(cashierI, fileScanner);
            loadingCustomer(cashierII, fileScanner);
            loadingCustomer(cashierIII, fileScanner);
            loadingCustomer(waitingQueue, fileScanner);
            fileScanner.close();
            System.out.println("Data is loaded successfully");

        }catch (Exception e){
            System.out.println("An error has occurred");
        }

    }
    public static void loadingCustomer(FoodQueue queue, Scanner input){
        for(int i = 0; i < queue.queueSize(); i++){
            String name = input.nextLine();
            if(name.equals("** ")){
                queue.settingPositionToNull(i);
            }else{
                String[] customerDetails = name.split("-", 4);
                queue.setCustomerDetails(i, customerDetails[0], customerDetails[1], customerDetails[2]);
            }
        }
    }
    public static void viewingTheRemainingBurgers(){
        System.out.println("You have " + burgers + " remaining burgers");
    }
    //Method of printing income details of each cashiers
    public static void printingIncomeDetails(FoodQueue cashierI, FoodQueue cashierII, FoodQueue cashierIII) {
        double totalIncome = cashierI.checkingIncome() + cashierII.checkingIncome() + cashierIII.checkingIncome();

        System.out.println("First Queue Income: " + cashierI.checkingIncome());
        System.out.println("Second Queue Income: " + cashierII.checkingIncome());
        System.out.println("Third Queue Income: " + cashierIII.checkingIncome());
        System.out.println("Total Income: " + totalIncome);
    }
    public static void addingBurgers(Scanner scan){
        try{
            System.out.print("How many burgers: ");
            int burgersAdded = scan.nextInt();
            if(burgersAdded > 0){
                if(burgersAdded+burgers > 50){
                    System.out.println("cannot store that much burgers");
                }else{
                    burgers += burgersAdded;
                    System.out.println(burgersAdded + " Burgers added");
                }
            }else{
                System.out.println("Invalid input");
            }

        }catch (Exception e){
            System.out.println("Invalid input");
        }


    }




}



