
package assignmentoop;
//some got use 
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
    

class Item {
        private static Scanner scanner = new Scanner(System.in);
    private static final String USER_FILE_PATH = "users.txt";
    private static final String ITEM_FILE_PATH = "items.txt";
    private static final String SUPPLIER_FILE_PATH = "suppliers.txt";
    private static final String SUPPLIER_ITEM_FILE_PATH = "supplier_items.txt";

    
    
    private int itemId;
    private String itemName;
    private String category;
    private int quantity;
    private double price;
    private Supplier supplier;
    
    
    
    public Item(int itemId, String itemName, String category, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }
 public Item() {
        this(0, "", "", 0, 0.0); 
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
        } else {
            System.out.println("Quantity cannot be negative.");
        }
    }

    public double calculateTotalValue() {
        return this.quantity * this.price;
    }
     @Override
    public String toString() {
        return "\n\t\t\t\t\t\tItem ID: " + itemId +
               "\n\t\t\t\t\t\tItem Name: " + itemName +
               "\n\t\t\t\t\t\tCategory: " + category +
               "\n\t\t\t\t\t\tQuantity: " + quantity +
               "\n\t\t\t\t\t\tPrice: RM " + String.format("%.2f", price) +
               "\n\t\t\t\t\t\tTotal Value: RM " + String.format("%.2f", calculateTotalValue());
    }
    
    
// Class-level variables for thresholds (Can change)
private static int minStockThreshold = 50;
private static int maxStockThreshold = 200;

public static void manageInventoryAlerts() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Manage Inventory Alerts ");
    System.out.println("\t\t\t\t\t\t=================================");


        int newMinThreshold = -1;
        int newMaxThreshold = -1;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("\t\t\t\t\t\tEnter new minimum stock threshold (current: " + minStockThreshold + "): ");
            try {
                newMinThreshold = scanner.nextInt();
                if (newMinThreshold > 0) {
                    validInput = true;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tThreshold must be greater than 0. Please try again." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        validInput = false; // Reset for max threshold input

        while (!validInput) {
            System.out.print("\t\t\t\t\t\tEnter new maximum stock threshold (current: " + maxStockThreshold + "): ");
            try {
                newMaxThreshold = scanner.nextInt();
                if (newMaxThreshold > 0) {
                    validInput = true;
                } else {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tThreshold must be greater than 0. Please try again." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        minStockThreshold = newMinThreshold;
        maxStockThreshold = newMaxThreshold;

        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tStock alert thresholds updated successfully." + ANSI_RESET);
    }

    
public static void displayInventoryStockLevel() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Inventory Stock Level ");
    System.out.println("\t\t\t\t\t\t=================================");

    try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                System.out.println("\t\t\t\t\t\t---------------------------------");
                System.out.println("\t\t\t\t\t\tItem ID: " + parts[0]);
                System.out.println("\t\t\t\t\t\tItem Name: " + parts[1]);
                System.out.println("\t\t\t\t\t\tCategory: " + parts[2]);
                System.out.println("\t\t\t\t\t\tQuantity: " + parts[3]);
                System.out.println("\t\t\t\t\t\tPrice: " + parts[4] + "\n");

                int quantity = Integer.parseInt(parts[3]); // Convert quantity to integer
                
                // Check for low stock
                if (quantity < minStockThreshold) {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\t*** Alert: Critical Low Stock! ***" + ANSI_RESET);
                    System.out.println("\t\t\t\t\t\tItem ID " + parts[0] + " has less than " + minStockThreshold + " units. Consider restocking immediately.\n\n");
                    suggestSuppliers(Integer.parseInt(parts[0]));
                }
                // Check for moderate low stock
                else if (quantity < maxStockThreshold) {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\tWarning: Low stock for Item ID " + parts[0] + ". Consider increasing the stock.\n\n" + ANSI_RESET);
                    suggestSuppliers(Integer.parseInt(parts[0]));
                }
                
                // Check for high stock
                else if (quantity > maxStockThreshold) {
                    System.out.println(ANSI_RED + "\t\t\t\t\t\t*** Alert: Excess Stock! ***" + ANSI_RESET);
                    System.out.println("\t\t\t\t\t\tItem ID " + parts[0] + " has more than " + maxStockThreshold + " units. Consider reducing stock or reviewing ordering policy.\n\n");
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading inventory data: " + e.getMessage() + ANSI_RESET);
    }
}

private static void suggestSuppliers(int itemId) {
    
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\t\t\t\t\t\tSuggested Suppliers for Item ID " + itemId + ":");

    try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_ITEM_FILE_PATH))) {
        String line;
        boolean found = false;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int supplierItemId = Integer.parseInt(parts[1]);
                if (supplierItemId == itemId) {
                    int supplierId = Integer.parseInt(parts[0]);
                    System.out.println(ANSI_GREEN + "\t\t\t\t\t\tSupplier ID: " + supplierId);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tNo suppliers found for this item." + ANSI_RESET);
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading supplier data: " + e.getMessage() + ANSI_RESET);
    }
}


//calculate total of each product categories and sum it 
public static void calculateStockValue() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Calculate Stock Value ");
    System.out.println("\t\t\t\t\t\t=================================");

    double totalValue = 0.0;
    Map<String, Double> categoryTotals = new HashMap<>();
    Map<String, Double> productTotals = new HashMap<>();

    try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                String category = parts[2];
                String productName = parts[1];
                int quantity = Integer.parseInt(parts[3]);
                double price = Double.parseDouble(parts[4]);
                double productValue = quantity * price;
                
                // Update total value
                totalValue += productValue;

                // Update category total
                categoryTotals.put(category, categoryTotals.getOrDefault(category, 0.0) + productValue);

                // Update product total
                productTotals.put(productName, productTotals.getOrDefault(productName, 0.0) + productValue);
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError calculating stock value: " + e.getMessage() + ANSI_RESET);
    }

    // Display category totals
    System.out.println("\n\t\t\t\t\t\tCategory-wise Breakdown:");
    for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
        System.out.println("\t\t\t\t\t\t" + entry.getKey() + ": RM " + String.format("%.2f", entry.getValue()));
    }
    System.out.println("\t\t\t\t\t\t---------------------------------");

    // Display product totals
    System.out.println("\n\t\t\t\t\t\tProduct-wise Breakdown:");
    for (Map.Entry<String, Double> entry : productTotals.entrySet()) {
        System.out.println("\t\t\t\t\t\t" + entry.getKey() + ": RM " + String.format("%.2f", entry.getValue()));
    }
    System.out.println("\t\t\t\t\t\t---------------------------------");

    // Display final total
    System.out.println("\n\t\t\t\t\t\tFinal Total Stock Value: RM " + String.format("%.2f", totalValue));
    System.out.println("\t\t\t\t\t\t=================================");
}


//remove item

public static void removeItem() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Remove Item ");
    System.out.println("\t\t\t\t\t\t=================================");
    
       String filePath = "items.txt"; 
        readItemsFromFile(filePath);
    // Prompt the user to enter the item ID to remove
    int itemId = -1;
        boolean validInput = false;

        while (!validInput) {
            try {
                // Prompt the user to enter the item ID to remove
                System.out.print("\n\t\t\t\t\t\tEnter Item ID to remove: ");
                itemId = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                validInput = true; // Input is valid, exit loop
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "\t\t\t\t\t\tInvalid input. Please enter a valid integer." + ANSI_RESET);
                scanner.nextLine(); // Clear the invalid input
            }
        }

        if (removeItemFromFile(itemId)) {
            System.out.println(ANSI_GREEN + "\t\t\t\t\t\tItem removed successfully." + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "\t\t\t\t\t\tItem not found." + ANSI_RESET);
        }
    }

public static boolean removeItemFromFile(int itemId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    
    File inputFile = new File(ITEM_FILE_PATH);
    File tempFile = new File("temp_items.txt");
    boolean removed = false;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                int fileItemId = Integer.parseInt(parts[0]);
                if (fileItemId != itemId) {
                    bw.write(line);
                    bw.newLine();
                } else {
                    removed = true; // Item found and removed
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError removing item: " + e.getMessage() + ANSI_RESET);
        return false;
    }

    if (!inputFile.delete()) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not delete original item file." + ANSI_RESET);
        return false;
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not rename temporary item file." + ANSI_RESET);
        return false;
    }

    return removed;
}
           public static void readItemsFromFile(String filePath) {
        final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int itemId = Integer.parseInt(parts[0]);
                    String itemName = parts[1];
                    String category = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);

                    //  Item object
                    Item item = new Item(itemId, itemName, category, quantity, price);

                    // Print the item details using the toString() method
                    System.out.println(item.toString());
                    System.out.println(); // Add a blank line between items
                } else {
                    System.out.println(ANSI_RED + "Error: Invalid item data format." + ANSI_RESET);
                }
            }
        } catch (IOException e) {
            System.out.println(ANSI_RED + "Error reading the file: " + e.getMessage() + ANSI_RESET);
        }
    }         
}
