/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author acer
 */

class Order {
        private static final String USER_FILE_PATH = "users.txt";
    private static final String ITEM_FILE_PATH = "items.txt";
    private static final String SUPPLIER_FILE_PATH = "suppliers.txt";
    private static final String SUPPLIER_ITEM_FILE_PATH = "supplier_items.txt";

    private int orderId;
    private User user;
    private Supplier supplier;
    private Item item;
    private int quantity;

    public Order(int orderId, User user, Supplier supplier, Item item, int quantity) {
        this.orderId = orderId;
        this.user = user;
        this.supplier = supplier;
        this.item = item;
        this.quantity = quantity;
    }
    public Order() {
        this(0, new User(), new Supplier(), new Item(), 0); 
    }
    
    public int getOrderId() {
        return orderId;
    }

    public User getUser() {
        return user;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
    
    //codeeeeeee
    
        private static Scanner scanner = new Scanner(System.in);
        
    public static void viewOrderHistory() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Order History ");
    System.out.println("\t\t\t\t\t\t=================================");

    try (BufferedReader br = new BufferedReader(new FileReader("order_history.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) { // Updated to check for 6 parts
                System.out.println("\t\t\t\t\t\tOrder ID: " + parts[0]);
                System.out.println("\t\t\t\t\t\tSupplier ID: " + parts[1]);
                System.out.println("\t\t\t\t\t\tItem ID: " + parts[2]);
                System.out.println("\t\t\t\t\t\tQuantity: " + parts[3]);
                System.out.println("\t\t\t\t\t\tDate: " + parts[4]);
                System.out.println("\t\t\t\t\t\tTotal Amount: RM " + parts[5]); // Display the total amount
                System.out.println("\t\t\t\t\t\t---------------------------------");
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading order history: " + e.getMessage() + ANSI_RESET);
    }
}

public static void orderFromSupplier() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Order From Supplier ");
    System.out.println("\t\t\t\t\t\t=================================");

    while (true) {
        viewSuppliers();

        int supplierId;
        while (true) {
            System.out.print("\t\t\t\t\t\tEnter Supplier ID to order from (or type 0 to exit): ");
            if (scanner.hasNextInt()) {
                supplierId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (supplierId == 0) {
                    System.out.println("\t\t\t\t\t\tOrder process exited.");
                    return; // Exit if the user enters 0
                } else if (supplierExists(supplierId)) {
                    break; // Valid Supplier ID entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid Supplier ID. Please try again." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid Supplier ID." + ANSI_RESET);
                scanner.next(); // Clear the invalid input
            }
        }

        displaySupplierItems(supplierId);

        int itemId;
        while (true) {
            System.out.print("\t\t\t\t\t\tEnter Item ID to order (or type 0 to exit): ");
            if (scanner.hasNextInt()) {
                itemId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (itemId == 0) {
                    System.out.println("\t\t\t\t\t\tOrder process exited.");
                    return; // Exit if the user enters 0
                } else if (supplierHasItem(supplierId, itemId)) {
                    break; // Valid Item ID entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid Item ID. Please try again." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid Item ID." + ANSI_RESET);
                scanner.next(); // Clear the invalid input
            }
        }

        int quantity;
        while (true) {
            System.out.print("\n\t\t\t\t\t\tEnter Quantity (or type 0 to exit): ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                if (quantity == 0) {
                    System.out.println("\t\t\t\t\t\tOrder process exited.");
                    return; // Exit if the user enters 0
                } else if (quantity > 0) {
                    break; // Valid quantity entered
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid quantity. Please enter a positive number." + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "\n\t\t\t\t\t\tInvalid input. Please enter a valid quantity." + ANSI_RESET);
                scanner.next(); // Clear the invalid input
            }
        }

        // Check stock availability
        int availableStock = getSupplierItemQuantity(supplierId, itemId);
        if (availableStock < quantity) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError: Not enough stock from supplier for item ID " + itemId + ANSI_RESET);
            continue; // Allow user to re-enter or exit
        }

        double itemPrice = getItemPrice(itemId);
        double totalPrice = itemPrice * quantity;

        System.out.println("\n\t\t\t\t\t\tItem ID: " + itemId);
        System.out.println("\t\t\t\t\t\tQuantity: " + quantity);
        System.out.println("\t\t\t\t\t\tTotal Price: RM " + String.format("%.2f", totalPrice));

        System.out.print("\n\t\t\t\t\t\tConfirm order? (y/n): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        if (confirmation.equals("y")) {
            // Process payment and place the order
            if (processPayment(totalPrice)) {
                // Update supplier's stock
                if (updateSupplierStock(supplierId, itemId, -quantity)) {
                    // Update inventory
                    updateInventory(itemId, quantity);

                    // Generate Order ID
                    int orderId = generateOrderId();

                    // Log the order
                    logOrder(orderId, supplierId, itemId, quantity, new Date(), totalPrice);

                    System.out.println("\t\t\t\t\t\tOrder placed successfully.");
                            // Generate Invoice

                     generateInvoice(orderId);
                } else {
                    System.out.println("\t\t\t\t\t\tError updating supplier's stock.");
                }
            } else {
                System.out.println("\t\t\t\t\t\tPayment failed. Order not placed.");
            }
            break; // Exit after processing
        } else {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tOrder cancelled by the user." + ANSI_RESET);
            return; // Exit process
        }
    }
}

private static boolean supplierExists(int supplierId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3 && Integer.parseInt(parts[0]) == supplierId) {
                return true;
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError checking supplier data: " + e.getMessage() + ANSI_RESET);
    }
    return false;
}

private static boolean supplierHasItem(int supplierId, int itemId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                int fileItemId = Integer.parseInt(parts[1]);
                if (fileSupplierId == supplierId && fileItemId == itemId) {
                    return true;
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError checking supplier item data: " + e.getMessage() + ANSI_RESET);
    }
    return false;
}

private static int getSupplierItemQuantity(int supplierId, int itemId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                int fileItemId = Integer.parseInt(parts[1]);
                if (fileSupplierId == supplierId && fileItemId == itemId) {
                    return Integer.parseInt(parts[2]);
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError reading supplier item quantity: " + e.getMessage() + ANSI_RESET);
    }
    return 0; // Return 0 if not found
}

private static double getItemPrice(int itemId) {
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
                    return Double.parseDouble(parts[4]); // Return item price
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading item data: " + e.getMessage() + ANSI_RESET);
    }
    return 0.0;
}
  //                              Payment


private static boolean processPayment(double amount) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Payment ");
    System.out.println("\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\tChoose Payment Method:");
    System.out.println("\t\t\t\t\t\t1. Credit Card");
    System.out.println("\t\t\t\t\t\t2. PayPal");
    System.out.println("\t\t\t\t\t\t3. E-Wallet");
    System.out.println("\t\t\t\t\t\t4. Cash");
    System.out.print("\t\t\t\t\t\tEnter your choice: ");
    
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    Payment payment = null;
    switch (choice) {
        case 1:
            // Handle Credit Card payment with validation
            String cardNumber;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter Card Number: ");
                cardNumber = scanner.nextLine().trim();
                if (cardNumber.matches("\\d{16}")) { // Example: Simple validation for 16-digit card number
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid card number. Please enter a valid 16-digit card number." + ANSI_RESET);
                }
            }

            String cardHolderName;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter Card Holder Name: ");
                cardHolderName = scanner.nextLine().trim();
                if (!cardHolderName.isEmpty()) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tCard holder name cannot be empty." + ANSI_RESET);
                }
            }

            payment = new CreditCardPayment(1, null, amount, new Date(), cardNumber, cardHolderName);
            break;
        
        case 2:
            // Handle PayPal payment with validation
            String email;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter PayPal Email: ");
                email = scanner.nextLine().trim();
                if (email.contains("@") && email.contains(".")) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid email address. Please enter a valid email." + ANSI_RESET);
                }
            }

            payment = new PayPalPayment(1, null, amount, new Date(), email);
            break;

        case 3:
        	    displayEWalletImage(); // Display image for E-Wallet
            // Handle E-Wallet payment with validation and image display
            String eWalletProvider;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter E-Wallet Provider: ");
                eWalletProvider = scanner.nextLine().trim();
                if (!eWalletProvider.isEmpty()) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tE-Wallet provider cannot be empty." + ANSI_RESET);
                }
            }

            String transactionId;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter Transaction ID: ");
                transactionId = scanner.nextLine().trim();
                if (!transactionId.isEmpty()) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tTransaction ID cannot be empty." + ANSI_RESET);
                }
            }

        

            payment = new EWalletPayment(1, null, amount, new Date(), eWalletProvider, transactionId);
            break;

        case 4:
            // Handle Cash payment with validation
            String cashReceivedBy;
            while (true) {
                System.out.print("\n\t\t\t\t\t\tEnter Cash Receiver Name: ");
                cashReceivedBy = scanner.nextLine().trim();
                if (!cashReceivedBy.isEmpty()) {
                    break;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tReceiver name cannot be empty." + ANSI_RESET);
                }
            }

            payment = new CashPayment(1, null, amount, new Date(), cashReceivedBy);
            break;

        default:
            System.out.println(ANSI_RED + "\n\t\t\t\t\t\tInvalid choice. Payment not processed." + ANSI_RESET);
            return false;
    }

    payment.processPayment();
    return true;
}
private static void displayEWalletImage() {
    // Create a JFrame to hold the image
    JFrame frame = new JFrame("E-Wallet Payment");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setSize(800, 800); // Set the size of the frame

    // Load the image from the file uploaded
    ImageIcon imageIcon = new ImageIcon("2.png"); // Replace with your image path
    JLabel label = new JLabel(imageIcon);

    frame.add(label);
    frame.setLocationRelativeTo(null); // Center the frame
    frame.setVisible(true);

    // Wait for 2 seconds to display the image before showing the message
    try {
        Thread.sleep(10000); // 10000 milliseconds = 10 seconds
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Show the message dialog after displaying the image
    JOptionPane.showMessageDialog(frame, "\t\t\t\t\t\tE-Wallet Payment Processing...");

    frame.dispose();
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
// order
private static int generateOrderId() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    int orderId = 1;
    try (BufferedReader br = new BufferedReader(new FileReader("order_history.txt"))) {
        while (br.readLine() != null) {
            orderId++;
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError generating order ID: " + e.getMessage() + ANSI_RESET);
    }
    return orderId;
}

private static void logOrder(int orderId, int supplierId, int itemId, int quantity, Date date, double totalAmount) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("order_history.txt", true))) {
        bw.write(orderId + "," + supplierId + "," + itemId + "," + quantity + "," + date.toString() + "," + totalAmount);
        bw.newLine();
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError logging order: " + e.getMessage() + ANSI_RESET);
    }
}
private static void displaySupplierItems(int supplierId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Supplier Items ");
    System.out.println("\t\t\t\t\t\t=================================");

    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                int fileItemId = Integer.parseInt(parts[1]);
                int quantity = Integer.parseInt(parts[2]);

                if (fileSupplierId == supplierId) {
                    found = true;
                    System.out.println("\t\t\t\t\t\tItem ID: " + fileItemId);
                    System.out.println("\t\t\t\t\t\tItem Name: " + getItemName(fileItemId));
                    System.out.println("\t\t\t\t\t\tQuantity Available: " + quantity);
                    System.out.println("\t\t\t\t\t\t---------------------------------");
                }
            }
        }

        if (!found) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tNo items found for this supplier." + ANSI_RESET);
        } else {
            System.out.println("\n\t\t\t\t\t\t=================================");
            System.out.println("\t\t\t\t\t\tOther Suppliers with this Item:");
            System.out.println("\t\t\t\t\t\t=================================");
            try (BufferedReader br2 = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
                while ((line = br2.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 3) {
                        int fileSupplierId = Integer.parseInt(parts[0]);
                        int fileItemId = Integer.parseInt(parts[1]);
                        int quantity = Integer.parseInt(parts[2]);

                        if (fileSupplierId != supplierId && checkSupplierHasItem(fileSupplierId, fileItemId)) {
                            System.out.println("\t\t\t\t\t\tSupplier ID: " + fileSupplierId);
                            System.out.println("\t\t\t\t\t\tQuantity Available: " + quantity);
                            System.out.println("\t\t\t\t\t\t---------------------------------");
                        }
                    }
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading supplier items: " + e.getMessage() + ANSI_RESET);
    }
}

    private static String getItemName(int itemId) {
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
                        return parts[1]; // Return item name
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading item data: " + e.getMessage() + ANSI_RESET);
        }
        return "Unknown";
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

    private static boolean checkSupplierHasItem(int supplierId, int itemId) {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
        try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int fileSupplierId = Integer.parseInt(parts[0]);
                    int fileItemId = Integer.parseInt(parts[1]);
                    if (fileSupplierId == supplierId && fileItemId == itemId) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError checking supplier item data: " + e.getMessage() + ANSI_RESET);
        }
        return false;
    }

    private static void viewSuppliers() {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
        System.out.println("\n\t\t\t\t\t\t=================================");
        System.out.println("\t\t\t\t\t\t Suppliers ");
        System.out.println("\t\t\t\t\t\t=================================");

        try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    System.out.println("\t\t\t\t\t\tSupplier ID: " + parts[0]);
                    System.out.println("\t\t\t\t\t\tSupplier Name: " + parts[1]);
                    System.out.println("\t\t\t\t\t\tContact: " + parts[2]);
                    System.out.println("\t\t\t\t\t\t---------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading supplier data: " + e.getMessage() + ANSI_RESET);
        }
    }
//invoice part 
//generate invoice part
private static void generateInvoice(int orderId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";

    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Generating Invoice for Order ID: " + orderId);
    System.out.println("\t\t\t\t\t\t=================================");

    // Retrieve order details (assuming you have an order file or list)
    List<String> orderItems = new ArrayList<>();
    double totalAmount = 0.0;
    String invoiceContent = "";
    String supplierInfo = "";
    String userInfo = "";

    try (BufferedReader br = new BufferedReader(new FileReader("order_history.txt"))) {
        String line;
        boolean foundOrder = false;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6 && Integer.parseInt(parts[0]) == orderId) {
                foundOrder = true;
                int supplierId = Integer.parseInt(parts[1]);
                String itemId = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                double itemPrice = getItemPrice(Integer.parseInt(itemId));
                double itemTotal = itemPrice * quantity;

                orderItems.add("Item ID: " + itemId + " | Quantity: " + quantity + " | Unit Price: RM " + itemPrice + " | Total: RM " + itemTotal);
                totalAmount += itemTotal;

                // Get supplier information
                supplierInfo = getSupplierDetails(supplierId);

                // Get user information (assuming userId is part of the order record)
              
            }
        }

        if (foundOrder) {
            invoiceContent += "\n\t\t\t\t\t\tU     U   N     N  IIIII  CCCCC   OOO   RRRR   N     N";
            invoiceContent += "\n\t\t\t\t\t\tU     U   NN    N    I   C       O   O  R   R  NN    N";
            invoiceContent += "\n\t\t\t\t\t\tU     U   N N   N    I   C       O   O  RRRR   N N   N";
            invoiceContent += "\n\t\t\t\t\t\tU     U   N  N  N    I   C       O   O  R R    N  N  N";
            invoiceContent += "\n\t\t\t\t\t\tU     U   N   N N    I   C       O   O  R  R   N   N N";
            invoiceContent += "\n\t\t\t\t\t\t UUUUU    N     N  IIIII  CCCCC   OOO   R   R  N     N";
     
            invoiceContent += "\n\t\t\t\t\t\t=================================";
            invoiceContent += "\n\t\t\t\t\t\tInvoice Details for Order ID: " + orderId;
            invoiceContent += "\n\t\t\t\t\t\t=================================\n";
            invoiceContent += "\t\t\t\t\t\tSupplier Information: \n\t\t\t\t\t\t" + supplierInfo + "\n";

            invoiceContent += "\t\t\t\t\t\t=================================\n";
            for (String orderItem : orderItems) {
                invoiceContent += "\t\t\t\t\t\t" + orderItem + "\n";
            }
            invoiceContent += "\n\t\t\t\t\t\tTotal Amount: RM " + String.format("%.2f", totalAmount);
            System.out.println(invoiceContent);
            saveInvoiceToFile(orderId, invoiceContent);
        } else {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tOrder not found." + ANSI_RESET);
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError generating invoice: " + e.getMessage() + ANSI_RESET);
    }
}
private static String getSupplierDetails(int supplierId) {
    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (Integer.parseInt(parts[0]) == supplierId) {
                return "Supplier ID: " + parts[0] + " | Name: " + parts[1] + " | Contact: " + parts[2];
            }
        }
    } catch (IOException e) {
        System.out.println("\u001B[31mError loading supplier data: " + e.getMessage() + "\u001B[0m");
    }
    return "Unknown Supplier";
}
//different invoice different text file
private static void saveInvoiceToFile(int orderId, String invoiceContent) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    String fileName = "invoice_order_" + orderId + ".txt";
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        bw.write(invoiceContent);
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tInvoice saved to " + fileName + ANSI_RESET);
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError saving invoice: " + e.getMessage() + ANSI_RESET);
    }
}

}

