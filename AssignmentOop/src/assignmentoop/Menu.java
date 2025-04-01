package assignmentoop;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;


public class Menu {

    private static final String USER_FILE_PATH = "users.txt";
    private static final String ITEM_FILE_PATH = "items.txt";
    private static final String SUPPLIER_FILE_PATH = "suppliers.txt";
    private static final String SUPPLIER_ITEM_FILE_PATH = "supplier_items.txt";

    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;

    public static void main(String[] args) {
        // Color codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_PURPLE = "\u001B[35m";

        displayImage();
        displayWelcomeScreen();

        int option;
        while (true) {
            System.out.println(ANSI_CYAN + "\t\t\t\t\t\t+--------------------------------------------------+");
            System.out.println("\t\t\t\t\t\t|              " + ANSI_PURPLE + "Inventory Management System" + ANSI_CYAN + "          |");
            System.out.println(ANSI_CYAN + "\t\t\t\t\t\t+--------------------------------------------------+\n" + ANSI_RESET);

            System.out.println(ANSI_BLUE + "\t\t\t\t\t\t| 1. Register                                      |");
            System.out.println(ANSI_BLUE + "\t\t\t\t\t\t| 2. Login                                         |");
            System.out.println(ANSI_BLUE + "\t\t\t\t\t\t| 3. Exit                                          |" + ANSI_RESET);
            System.out.println(ANSI_CYAN + "\t\t\t\t\t\t+--------------------------------------------------+\n" + ANSI_RESET);

            System.out.print("\t\t\t\t\t\tEnter your choice (1-3): ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (option) {
                    case 1:
                        User.registerUser();
                        break;
                    case 2:
                        if (loginUser()) {
                            showMainMenu();
                        }
                        break;
                    case 3:
                        System.out.println(ANSI_RED + "\t\t\t\t\t\tThank you for using the Inventory Management System.");
                        System.out.println(ANSI_BLUE + "\t\t\t\t\t\tGoodbye!" + ANSI_RESET);
                        scanner.close();
                        return;
                    default:
                        System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. Please enter a number between 1 and 4." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid number (1-4)." + ANSI_RESET);
                scanner.next(); // Clear the invalid input
            }
        }
    }

    private static void displayImage() {
        // Create a JFrame to hold the image
        JFrame frame = new JFrame("Welcome");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the frame

        // Load the image from a file
        ImageIcon imageIcon = new ImageIcon("1.png"); // Your image
        JLabel label = new JLabel(imageIcon);

        frame.add(label);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        // Wait for a few seconds or until the user closes the window
        JOptionPane.showMessageDialog(frame, "Click OK to continue");
        frame.dispose();
    }

    public static void displayWelcomeScreen() {
        // Define ANSI color codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";
        final String ANSI_YELLOW = "\u001B[33m";
        final String ANSI_BLUE = "\u001B[34m";
        final String ANSI_PURPLE = "\u001B[35m";
        final String ANSI_CYAN = "\u001B[36m";
        final String ANSI_WHITE = "\u001B[37m";

       System.out.println(ANSI_CYAN + "						      U     U   N     N  IIIII  CCCCC   OOO   RRRR   N     N ");
    System.out.println(ANSI_BLUE + "						      U     U   NN    N    I   C       O   O  R   R  NN    N ");
    System.out.println(ANSI_PURPLE + "						      U     U   N N   N    I   C       O   O  RRRR   N N   N ");
    System.out.println(ANSI_GREEN + "						      U     U   N  N  N    I   C       O   O  R R    N  N  N ");
    System.out.println(ANSI_YELLOW + "						      U     U   N   N N    I   C       O   O  R  R   N   N N ");
    System.out.println(ANSI_RED + "						       UUUUU    N     N  IIIII  CCCCC   OOO   R   R  N     N " + ANSI_RESET);

        // Colorful border and welcome text
        System.out.println(ANSI_PURPLE + "						=================================================================");
    System.out.println("						|           " + ANSI_GREEN + "Welcome to the Inventory Management System" + ANSI_PURPLE + "         |");
    System.out.println("						=================================================================" + ANSI_RESET);
    }

    private static boolean loginUser() {
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_BLUE = "\u001B[34m";

        System.out.println("\n\t\t\t\t\t\t=================================================");
        System.out.println("\t\t\t\t\t\t User Login ");
        System.out.println("\t\t\t\t\t\t=================================");
        System.out.print("\t\t\t\t\t\tEnter username: ");
        String username = scanner.nextLine();
        System.out.print("\n\t\t\t\t\t\tEnter password: ");
        String password = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String fileUsername = parts[1];
                    String filePassword = parts[2];
                    if (fileUsername.equals(username) && filePassword.equals(password)) {
                        currentUser = new User(Integer.parseInt(parts[0]), fileUsername, filePassword, parts[3], parts[4], parts[5]);

                        // Create a LoginAction instance and perform the login action
                        UserActionable loginAction = new LoginAction(currentUser);
                        loginAction.performAction();

                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading user data: " + e.getMessage() + ANSI_RESET);
        }

        System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid username or password. Please try again." + ANSI_RESET);
        return false;
    }

   private static void stockOut() {
       final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Stock Out ");
    System.out.println("\t\t\t\t\t\t=================================");
        int itemId = -1;
        int quantity = -1;
        boolean validInput = false;

        // Validate Item ID input
        while (!validInput) {
            try {
                System.out.print("\n\t\t\t\t\t\tEnter Item ID to stock out: ");
                itemId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true; // Input is valid, exit loop
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer for Item ID." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        validInput = false; // Reset for quantity input validation

        // Validate Quantity input
        while (!validInput) {
            try {
                System.out.print("\n\t\t\t\t\t\tEnter Quantity: ");
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (quantity > 0) {
                    validInput = true; // Input is valid, exit loop
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tQuantity must be greater than zero." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer for Quantity." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        // Accept Remark input
        System.out.print("\n\t\t\t\t\t\tEnter Remark: ");
        String remark = scanner.nextLine();

        // Get current date
        Date date = new Date();

        // Update inventory and save stock out details
        updateInventory(itemId, -quantity);
        saveStockOutDetails(itemId, quantity, remark, date);

        System.out.println(ANSI_GREEN + "\n\t\t\t\t\t\tStock out completed successfully." + ANSI_RESET);
    }

private static void updateInventory(int itemId, int quantityChange) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    List<String> items = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                int fileItemId = Integer.parseInt(parts[0]);
                if (fileItemId == itemId) {
                    int newQuantity = Integer.parseInt(parts[3]) + quantityChange;
                    if (newQuantity < 0) {
                        System.out.println(ANSI_RED + "\t\t\t\t\t\tError: Not enough stock for item ID " + itemId + ANSI_RESET);
                        return;
                    }
                    parts[3] = String.valueOf(newQuantity);
                    System.out.println("\n\t\t\t\t\t\tUpdated quantity for item ID " + itemId + " to " + newQuantity);
                }
                items.add(String.join(",", parts));
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError updating inventory: " + e.getMessage() + ANSI_RESET);
    }

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(ITEM_FILE_PATH))) {
        for (String item : items) {
            bw.write(item);
            bw.newLine();
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving updated inventory: " + e.getMessage() + ANSI_RESET);
    }
}
private static void saveStockOutDetails(int itemId, int quantity, String remark, Date date) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("stock_out_details.txt", true))) {
        bw.write(itemId + "," + quantity + "," + remark + "," + date.toString());
        bw.newLine();
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving stock out details: " + e.getMessage() + ANSI_RESET);
    }
}
private static void viewStockOutDetails() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Stock Out Details ");
    System.out.println("\t\t\t\t\t\t=================================");

    try (BufferedReader br = new BufferedReader(new FileReader("stock_out_details.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                System.out.println("\t\t\t\t\t\tItem ID: " + parts[0]);
                System.out.println("\t\t\t\t\t\tQuantity: " + parts[1]);
                System.out.println("\t\t\t\t\t\tRemark: " + parts[2]);
                System.out.println("\t\t\t\t\t\tDate: " + parts[3]);
                System.out.println("\t\t\t\t\t\t---------------------------------");
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading stock out details: " + e.getMessage() + ANSI_RESET);
    }
}
private static void stockIn() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Stock In ");
    System.out.println("\t\t\t\t\t\t=================================");
    
    int itemId = 0;
    int quantity = 0;
    boolean validInput = false;
    
    while (!validInput) {
            System.out.print("\n\t\t\t\t\t\tEnter Item ID to stock in: ");
            if (scanner.hasNextInt()) {
                itemId = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter an integer value for Item ID." + ANSI_RESET);
                scanner.next(); // Consume invalid input
            }
        }

        validInput = false;
        while (!validInput) {
            System.out.print("\n\t\t\t\t\t\tEnter Quantity: ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                validInput = true;
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter an integer value for Quantity." + ANSI_RESET);
                scanner.next(); // Consume invalid input
            }
        }
        
        scanner.nextLine(); // Consume newline character
        System.out.print("\n\t\t\t\t\t\tEnter Remark: ");
        String remark = scanner.nextLine();

        // Update inventory
        updateInventory(itemId, quantity);

        // Save stock in details
        saveStockInDetails(itemId, quantity, remark, new Date());
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tStock in completed successfully." + ANSI_RESET);
    }

private static void saveStockInDetails(int itemId, int quantity, String remark, Date date) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("stock_in_details.txt", true))) {
        bw.write(itemId + "," + quantity + "," + remark + "," + date.toString());
        bw.newLine();
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving stock in details: " + e.getMessage() + ANSI_RESET);
    }
}
private static void viewStockInDetails() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Stock In Details ");
    System.out.println("\t\t\t\t\t\t=================================");

    try (BufferedReader br = new BufferedReader(new FileReader("stock_in_details.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                System.out.println("\t\t\t\t\t\tItem ID: " + parts[0]);
                System.out.println("\t\t\t\t\t\tQuantity: " + parts[1]);
                System.out.println("\t\t\t\t\t\tRemark: " + parts[2]);
                System.out.println("\t\t\t\t\t\tDate: " + parts[3]);
                System.out.println("\t\t\t\t\t\t---------------------------------");
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading stock in details: " + e.getMessage() + ANSI_RESET);
    }
}

//                                  Feedback use Jpanel

private static void provideFeedback() {
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t|      Provide Feedback      |");
    System.out.println("\t\t\t\t\t\t=================================\n");

    // Use JOptionPane to capture feedback 
    String feedback = JOptionPane.showInputDialog(null, 
        "Please provide your feedback:", 
        "Feedback", 
        JOptionPane.QUESTION_MESSAGE);

    // Check if feedback is provided or canceled
    if (feedback != null && !feedback.trim().isEmpty()) {
        // Display the feedback back to the user 
        JOptionPane.showMessageDialog(null, 
            "Thank you for your feedback:\n\n" + feedback, 
            "Feedback Received", 
            JOptionPane.INFORMATION_MESSAGE);
        
        //  save the feedback to a file
        saveFeedbackToFile(feedback);
    } else {
        JOptionPane.showMessageDialog(null, 
            "No feedback provided.", 
            "Feedback", 
            JOptionPane.WARNING_MESSAGE);
    }
}

private static void saveFeedbackToFile(String feedback) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    String feedbackFilePath = "feedback.txt";
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(feedbackFilePath, true))) {
        writer.write("Feedback from user " + currentUser.getUsername() + ":");
        writer.newLine();
        writer.write(feedback);
        writer.newLine();
        writer.write("-----------------------------------");
        writer.newLine();
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tFeedback saved successfully." + ANSI_RESET);
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving feedback: " + e.getMessage() + ANSI_RESET);
    }
}

  
    private static void searchInventory() {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Search Inventory ");
    System.out.println("\t\t\t\t\t\t=================================");
    
    // Prompt the user to choose the search criteria
            int choice = 0;
        boolean validChoice = false;

        // Prompt the user to choose the search criteria
        while (!validChoice) {
            System.out.println("\t\t\t\t\t\tChoose search criteria:");
            System.out.println("\t\t\t\t\t\t1. ID");
            System.out.println("\t\t\t\t\t\t2. Name");
            System.out.println("\t\t\t\t\t\t3. Category");
            System.out.print("\t\t\t\t\t\tEnter choice (1-3): ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); 

                if (choice >= 1 && choice <= 3) {
                    validChoice = true;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. Please enter a number between 1 and 3." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a number." + ANSI_RESET);
                scanner.next(); // Consume invalid input
            }
        }

    System.out.print("\n\t\t\t\t\t\tEnter search keyword: ");
        String keyword = scanner.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
            String line;
            boolean found = false;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    String itemID = parts[0];
                    String itemName = parts[1];
                    String category = parts[2];
                    boolean match = false;
                    
                    // Search based on the user choice
                    if (choice == 1 && itemID.toLowerCase().contains(keyword.toLowerCase())) {
                        match = true;
                    } else if (choice == 2 && itemName.toLowerCase().contains(keyword.toLowerCase())) {
                        match = true;
                    } else if (choice == 3 && category.toLowerCase().contains(keyword.toLowerCase())) {
                        match = true;
                    }
                    
                    if (match) {
                        System.out.println("\n\t\t\t\t\t\tItem ID: " + itemID);
                        System.out.println("\t\t\t\t\t\tItem Name: " + itemName);
                        System.out.println("\t\t\t\t\t\tCategory: " + category);
                        System.out.println("\t\t\t\t\t\tQuantity: " + parts[3]);
                        System.out.println("\t\t\t\t\t\tPrice: " + parts[4]);
                        System.out.println("\t\t\t\t\t\t---------------------------------");
                        found = true;
                    }
                }
            }
        if (!found) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tNo items found matching the keyword." + ANSI_RESET);
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError searching inventory: " + e.getMessage() + ANSI_RESET);
    }
}
      
private static void showMainMenu() {
    int option;

   
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";

    while (true) {
        System.out.println(ANSI_CYAN + "\n\t\t\t\t\t\t=================================");
        System.out.println(ANSI_CYAN + "\t\t\t\t\t\t|      Inventory Management Main Menu      |");
        System.out.println(ANSI_CYAN + "\t\t\t\t\t\t=================================\n" + ANSI_RESET);

        System.out.println(ANSI_RED + "\t\t\t\t\t\t[1] Inventory Management" + ANSI_RESET);
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    1.  Inventory Stock Level");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    2.  Stock Out");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    3.  Stock In");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    4.  View Stock In Details");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    5.  Search Inventory");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    6.  Calculate Stock Value");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    7.  Return to Supplier");
        System.out.println(ANSI_RED + "\t\t\t\t\t\t    8.  Remove Item\n" + ANSI_RESET);

        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t[2] Order Management" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t    9.  Order From Supplier");
        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t   10.  View Stock Out Details");
        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t   11.  View Order History\n" + ANSI_RESET);

        System.out.println(ANSI_GREEN + "\t\t\t\t\t\t[3] Supplier and Profile Management" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\t   12.  Profile Management");
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\t   13.  Supplier Management\n" + ANSI_RESET);

        System.out.println(ANSI_BLUE + "\t\t\t\t\t\t[4] Reporting and User Management" + ANSI_RESET);
        System.out.println(ANSI_BLUE + "\t\t\t\t\t\t   14.  Report Generation");
        System.out.println(ANSI_BLUE + "\t\t\t\t\t\t   15.  User Management\n" + ANSI_RESET);

        System.out.println(ANSI_PURPLE + "\t\t\t\t\t\t[5] Feedback" + ANSI_RESET);
        System.out.println(ANSI_PURPLE + "\t\t\t\t\t\t   16.  Provide Feedback\n" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\t\t\t\t\t\t[6] Alerts Management" + ANSI_RESET);
        System.out.println(ANSI_CYAN + "\t\t\t\t\t\t   17.  Manage Stock Level\n" + ANSI_RESET);

        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t[7] Logout" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "\t\t\t\t\t\t   18.  Logout\n" + ANSI_RESET);

        System.out.println(ANSI_CYAN + "\t\t\t\t\t\t=================================");
        System.out.print(ANSI_GREEN + "\t\t\t\t\t\tEnter your choice: " + ANSI_RESET);
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            scanner.nextLine(); 

            switch (option) {
                case 1:
                   Item.displayInventoryStockLevel();
                    break;
                case 2:
                    stockOut();
                    break;
                case 3:
                    stockIn();
                    break;
                case 4:
                   viewStockInDetails();
                    break;
                case 5:
                    searchInventory();
                    break;
                case 6:
                    Item.calculateStockValue(); 
                    break;
                case 7:
                     returnToSupplier();
                    break;
                case 8:
                   Item.removeItem(); 
                    break;
                case 9:
                    Order.orderFromSupplier();
                    break;
                case 10:
                    viewStockOutDetails();
                    break;
                case 11:
                    Order.viewOrderHistory();
                    break;
                case 12:
                     profileManagement();
                    break;
                case 13:
                   supplierManagement();
                    break;
                case 14:
                    generateReport();
                    break;
                case 15:
                  User.userManagement();
                    break;
                case 16:
                   provideFeedback();
                    break;
                case 17:
                   Item.manageInventoryAlerts();
                    break;
                case 18:
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tLogging out..." + ANSI_RESET);
                    currentUser = null;
                    return;
                default:
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. Please try again." + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a number." + ANSI_RESET);
            scanner.next(); 
        }
    }
}


private static void generateReport() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Report Generation ");
    System.out.println("\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t1. Inventory Stock Levels Report");
    System.out.println("\t\t\t\t\t\t2. Orders History Report");
    System.out.println("\t\t\t\t\t\t3. Supplier Details Report");
    System.out.println("\t\t\t\t\t\t4. Exception Report (Low/Max Stock Levels)");
    System.out.println("\t\t\t\t\t\t5. Summary Report");
    System.out.println("\t\t\t\t\t\t6. Generate All Reports");
    
    int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\t\t\t\t\t\tEnter your choice: ");
            try {
                choice = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter an integer." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        switch (choice) {
            case 1:
                ReportGenerator inventoryReport = new InventoryReport();
                inventoryReport.generateReport();
                break;
            case 2:
                ReportGenerator orderHistoryReport = new OrderHistoryReport();
                orderHistoryReport.generateReport();
                break;
            case 3:
                ReportGenerator supplierDetailsReport = new SupplierDetailsReport();
                supplierDetailsReport.generateReport();
                break;
            case 4:
                ReportGenerator exceptionReport = new ExceptionReport();
                exceptionReport.generateReport();
                break;
            case 5:
                ReportGenerator summaryReport = new SummaryReport();
                summaryReport.generateReport();
                break;
            case 6:
                new InventoryReport().generateReport();
                new OrderHistoryReport().generateReport();
                new SupplierDetailsReport().generateReport();
                new ExceptionReport().generateReport();
                new SummaryReport().generateReport();
                break;
            default:
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. No report generated." + ANSI_RESET);
        }
    }
 private static void profileManagement() {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
        System.out.println("\n\t\t\t\t\t\t=================================");
        System.out.println("\t\t\t\t\t\t Profile Management ");
        System.out.println("\t\t\t\t\t\t=================================");
        System.out.println("\t\t\t\t\t\t1. View Profile");
        System.out.println("\t\t\t\t\t\t2. Update Profile");
        System.out.print("\t\t\t\t\t\tEnter your choice: ");
       

        int option = getValidChoice();

        switch (option) {
            case 1:
                viewProfile();
                break;
            case 2:
                updateProfile();
                break;
            default:
                System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
        }
    }

    private static int getValidChoice() {
        
        while (!scanner.hasNextInt()) {
            System.out.println("\u001B[31m\t\t\t\t\t\tInvalid input. Please enter a number.\u001B[0m");
            scanner.next(); // Consume invalid input
            System.out.print("\t\t\t\t\t\tEnter your choice: ");
        }
        return scanner.nextInt();
    }

    private static void viewProfile() {
        
        System.out.println("\n\t\t\t\t\t\t=================================");
        System.out.println("\t\t\t\t\t\t View Profile ");
        System.out.println("\t\t\t\t\t\t=================================");
        System.out.println("\t\t\t\t\t\tUser ID: " + currentUser.getUserId());
        System.out.println("\t\t\t\t\t\tUsername: " + currentUser.getUsername());
        System.out.println("\t\t\t\t\t\tIC: " + currentUser.getIc());
        System.out.println("\t\t\t\t\t\tEmail: " + currentUser.getEmail());
        System.out.println("\t\t\t\t\t\tPhone: " + currentUser.getPhone());
        System.out.println("\t\t\t\t\t\t=================================");
    }
    
    private static void updateProfile() {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Update Profile ");
    System.out.println("\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t1. Update Email");
    System.out.println("\t\t\t\t\t\t2. Update Phone");
    System.out.println("\t\t\t\t\t\t3. Update Both");
    System.out.print("\t\t\t\t\t\tEnter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    String newEmail = null;
    String newPhone = null;

    switch (choice) {
        case 1:
            // Validate email format
            while (true) {
                System.out.print("\t\t\t\t\t\tEnter new email: ");
                newEmail = scanner.nextLine();
                if (newEmail.contains("@") && newEmail.contains(".")) {
                    break; // Valid email entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid email format. Please try again." + ANSI_RESET);
                }
            }
            currentUser.setEmail(newEmail);
            break;
        case 2:
            // Validate phone number format
            while (true) {
                System.out.print("\t\t\t\t\t\tEnter new phone: ");
                newPhone = scanner.nextLine();
                if (newPhone.matches("\\d{10,11}")) { // Assuming phone numbers are 10-11 digits long
                    break; // Valid phone number entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid phone number format. Please enter a valid 10-11 digit phone number." + ANSI_RESET);
                }
            }
            currentUser.setPhone(newPhone);
            break;
        case 3:
            // Validate both email and phone number
            while (true) {
                System.out.print("\t\t\t\t\t\tEnter new email: ");
                newEmail = scanner.nextLine();
                if (newEmail.contains("@") && newEmail.contains(".")) {
                    break; // Valid email entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid email format. Please try again." + ANSI_RESET);
                }
            }
            while (true) {
                System.out.print("\t\t\t\t\t\tEnter new phone: ");
                newPhone = scanner.nextLine();
                if (newPhone.matches("\\d{10,11}")) {
                    break; // Valid phone number entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid phone number format. Please enter a valid 10-11 digit phone number." + ANSI_RESET);
                }
            }
            currentUser.setEmail(newEmail);
            currentUser.setPhone(newPhone);
            break;
        default:
            System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. Please try again." + ANSI_RESET);
            return; // Exit the method if the choice is invalid
    }

    // Update the user information in the file
    updateUserFile();

    // Perform the profile update action
    UserActionable updateAction = new ProfileUpdateAction(currentUser);
    updateAction.performAction();
}

private static void updateUserFile() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    File inputFile = new File(USER_FILE_PATH);
    File tempFile = new File("temp_users.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                int fileUserId = Integer.parseInt(parts[0]);
                if (fileUserId == currentUser.getUserId()) {
                    parts[4] = currentUser.getEmail();
                    parts[5] = currentUser.getPhone();
                }
                bw.write(String.join(",", parts));
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "Error updating user file: " + e.getMessage() + ANSI_RESET);
    }

    if (!inputFile.delete()) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not delete original user file." + ANSI_RESET);
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not rename temporary user file." + ANSI_RESET);
    }
}

private static void returnToSupplier() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Return to Supplier ");
    System.out.println("\t\t\t\t\t\t=================================");

    // Prompt the user to enter the item ID to return
        // Prompt the user to enter the item ID to return
        int itemId = getItemId();
        if (itemId == -1) return; // Invalid Item ID

        int quantity = getQuantity();
        if (quantity == -1) return; // Invalid Quantity

        // Check if the item exists and if there is enough stock
        int currentStock = getItemQuantity(itemId);
        if (currentStock < quantity) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError: Not enough stock to return." + ANSI_RESET);
            return;
        }

        // Reduce the stock in inventory
        updateInventory(itemId, -quantity);

        // Optionally, increase the stock for the supplier
        int supplierId = getSupplierId();
        if (supplierId > 0) {
            updateSupplierStock(supplierId, itemId, quantity);
        }

        // Log the return
        saveReturnToSupplierDetails(itemId, quantity, supplierId, new Date());

        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tReturn to supplier completed successfully." + ANSI_RESET);
    }

    private static int getItemId() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";
        System.out.print("\n\t\t\t\t\t\tEnter Item ID to return: ");
        while (!scanner.hasNextInt()) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer for Item ID." + ANSI_RESET);
            scanner.next(); // Consume invalid input
            System.out.print("\n\t\t\t\t\t\tEnter Item ID to return: ");
        }
        return scanner.nextInt();
    }

    private static int getQuantity() {
            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_RED = "\u001B[31m";
        System.out.print("\n\t\t\t\t\t\tEnter Quantity to return: ");
        while (!scanner.hasNextInt()) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer for Quantity." + ANSI_RESET);
            scanner.next(); // Consume invalid input
            System.out.print("\n\t\t\t\t\t\tEnter Quantity to return: ");
        }
        return scanner.nextInt();
    }

    private static int getSupplierId() {
        System.out.print("\n\t\t\t\t\t\tEnter Supplier ID (optional, 0 to skip): ");
        while (!scanner.hasNextInt()) {
            System.out.println("\t\t\t\t\t\tInvalid input. Please enter a valid integer for Supplier ID.");
            scanner.next(); // Consume invalid input
            System.out.print("\n\t\t\t\t\t\tEnter Supplier ID (optional, 0 to skip): ");
        }
        return scanner.nextInt();
    }

private static void saveReturnToSupplierDetails(int itemId, int quantity, int supplierId, Date date) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("return_to_supplier.txt", true))) {
        bw.write(itemId + "," + quantity + "," + supplierId + "," + date.toString());
        bw.newLine();
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving return to supplier details: " + e.getMessage() + ANSI_RESET);
    }
}

    private static int getItemQuantity(int itemId) {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
        try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int fileItemId = Integer.parseInt(parts[0]);
                    if (fileItemId == itemId) {
                        return Integer.parseInt(parts[3]); // Return item quantity
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading item data: " + e.getMessage() + ANSI_RESET);
        }
        return 0;
    }


private static boolean updateSupplierStock(int supplierId, int itemId, int quantityChange) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    File inputFile = new File(SUPPLIER_ITEM_FILE_PATH);
    File tempFile = new File("temp_supplier_items.txt");
    boolean updated = false;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                int fileItemId = Integer.parseInt(parts[1]);
                if (fileSupplierId == supplierId && fileItemId == itemId) {
                    int currentQuantity = Integer.parseInt(parts[2]);
                    int newQuantity = currentQuantity + quantityChange;
                    if (newQuantity < 0) {
                        System.out.println(ANSI_RED + "\t\t\t\t\t\tError: Not enough stock from supplier for item ID " + itemId + ANSI_RESET);
                        return false; // Not enough stock
                    }
                    parts[2] = String.valueOf(newQuantity);
                    updated = true;
                }
                bw.write(String.join(",", parts));
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError updating supplier stock: " + e.getMessage() + ANSI_RESET);
        return false;
    }

    if (!inputFile.delete()) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not delete original supplier item file." + ANSI_RESET);
        return false;
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not rename temporary supplier item file." + ANSI_RESET);
        return false;
    }

    return updated;
}
private static void supplierManagement() {
       final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Supplier Management ");
    System.out.println("\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t1. View Suppliers");
    System.out.println("\t\t\t\t\t\t2. Add Supplier");
    System.out.println("\t\t\t\t\t\t3. Update Supplier");
    System.out.println("\t\t\t\t\t\t4. Delete Supplier");
    System.out.println("\t\t\t\t\t\t5. Back to Main Menu");
        int choice = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\n\t\t\t\t\t\tEnter your choice: ");
            try {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 5) {
                    validInput = true;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tChoice out of range. Please enter a number between 1 and 5." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter an integer." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        switch (choice) {
            case 1:
                Supplier.viewSuppliers();
                break;
            case 2:
                 Supplier.addSupplier();
                break;
            case 3:
                Supplier. updateSupplier();
                break;
            case 4:
                Supplier. deleteSupplier();
                break;
            case 5:
                System.out.println(ANSI_YELLOW + "\t\t\t\t\t\tReturning to main menu..." + ANSI_RESET);
                return;
            default:
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid choice. No action taken." + ANSI_RESET);
        }
    }

}

