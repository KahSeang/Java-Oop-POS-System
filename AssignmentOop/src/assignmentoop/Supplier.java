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
import java.util.Scanner;

  public class Supplier {
          private static final String USER_FILE_PATH = "users.txt";
    private static final String ITEM_FILE_PATH = "items.txt";
    private static final String SUPPLIER_FILE_PATH = "suppliers.txt";
    private static final String SUPPLIER_ITEM_FILE_PATH = "supplier_items.txt";


    private static Scanner scanner = new Scanner(System.in);
            private int supplierId;
            private String supplierName;
            private String contact;
            private Item item;

            // Constructor without item composition
            public Supplier(int supplierId, String supplierName, String contact) {
                this.supplierId = supplierId;
                this.supplierName = supplierName;
                this.contact = contact;
            }

            // Constructor with item composition
            public Supplier(int supplierId, String supplierName, String contact, int itemId, String itemName, String category, int quantity, double price) {
                this.supplierId = supplierId;
                this.supplierName = supplierName;
                this.contact = contact;
                this.item = new Item(itemId, itemName, category, quantity, price); // Composed Item
            }


            //no arg
            public Supplier() {
                this(0, "", ""); 
            }

            public int getSupplierId() {
                return supplierId;
            }

            public String getSupplierName() {
                return supplierName;
            }

            public String getContact() {
                return contact;
            }

            public Item getItem() {
                return item;
            }

            public void setItem(int itemId, String itemName, String category, int quantity, double price) {
                this.item = new Item(itemId, itemName, category, quantity, price);
            }


            public String toFileString() {
                if (item != null) {
                    return supplierId + "," + supplierName + "," + contact + "," +
                           item.getItemId() + "," + item.getItemName() + "," +
                           item.getCategory() + "," + item.getQuantity() + "," +
                           item.getPrice();
                } else {
                    return supplierId + "," + supplierName + "," + contact;
                }
            }

          //because need to check some supplier has item some don have
            @Override
            public String toString() {
                if (item != null) {
                    return "Supplier ID: " + supplierId + ", Name: " + supplierName +
                           ", Contact: " + contact + ", Item: [" + item.toString() + "]";
                } else {
                    return "Supplier ID: " + supplierId + ", Name: " + supplierName +
                           ", Contact: " + contact;
                }
            }
            
            
            
public static void addSupplier() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Add New Supplier ");
    System.out.println("\t\t\t\t\t\t=================================");
    System.out.print("\n\t\t\t\t\t\tEnter Supplier Name: ");
    String name = scanner.nextLine();
    System.out.print("\n\t\t\t\t\t\tEnter Supplier Contact: ");
    String contact = scanner.nextLine();

    int newId = generateNewSupplierId(); // Generate a new unique ID
    Supplier newSupplier = new Supplier(newId, name, contact);
    saveSupplierToFile(newSupplier);
    System.out.println(ANSI_GREEN + "\t\t\t\t\t\tSupplier added successfully with Supplier ID: " + newId + ANSI_RESET);
}

public static void saveSupplierToFile(Supplier supplier) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    try (BufferedWriter bw = new BufferedWriter(new FileWriter("suppliers.txt", true))) {
        bw.write(supplier.toFileString());
        bw.newLine();
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\n\t\t\t\t\t\tError saving supplier data: " + e.getMessage() + ANSI_RESET);
    }
}

public static int generateNewSupplierId() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    int maxId = 0;
    try (BufferedReader br = new BufferedReader(new FileReader("suppliers.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                if (id > maxId) {
                    maxId = id;
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "Error generating new supplier ID: " + e.getMessage() + ANSI_RESET);
    }
    return maxId + 1; // Return the next ID
}

public static void updateSupplier() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Update Supplier ");
    System.out.println("\t\t\t\t\t\t=================================");
    viewSuppliers();
    System.out.print("\t\t\t\t\t\tEnter Supplier ID to update: ");
    int supplierId = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    System.out.print("\n\t\t\t\t\t\tEnter new Supplier Name: ");
    String newName = scanner.nextLine();
    System.out.print("\n\t\t\t\t\t\tEnter new Supplier Contact: ");
    String newContact = scanner.nextLine();

    if (updateSupplierInFile(supplierId, newName, newContact)) {
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tSupplier updated successfully." + ANSI_RESET);
    } else {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tSupplier not found." + ANSI_RESET);
    }
}

public static boolean updateSupplierInFile(int supplierId, String newName, String newContact) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    File inputFile = new File("suppliers.txt");
    File tempFile = new File("temp_suppliers.txt");
    boolean updated = false;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                if (fileSupplierId == supplierId) {
                    parts[1] = newName;
                    parts[2] = newContact;
                    updated = true;
                }
                bw.write(String.join(",", parts));
                bw.newLine();
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError updating supplier file: " + e.getMessage() + ANSI_RESET);
    }

    if (!inputFile.delete()) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not delete original supplier file." + ANSI_RESET);
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not rename temporary supplier file." + ANSI_RESET);
    }

    return updated;
}

public static void deleteSupplier() {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    System.out.println("\n\t\t\t\t\t\t=================================");
    System.out.println("\t\t\t\t\t\t Delete Supplier ");
    System.out.println("\t\t\t\t\t\t=================================");
    viewSuppliers();
    System.out.print("\t\t\t\t\t\tEnter Supplier ID to delete: ");
    int supplierId = scanner.nextInt();
    scanner.nextLine(); // Consume newline character

    if (deleteSupplierFromFile(supplierId)) {
        System.out.println(ANSI_GREEN + "\t\t\t\t\t\tSupplier deleted successfully." + ANSI_RESET);
    } else {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tSupplier not found." + ANSI_RESET);
    }
}

public static boolean deleteSupplierFromFile(int supplierId) {
    final String ANSI_RESET = "\u001B[0m";
    final String ANSI_RED = "\u001B[31m";
    final String ANSI_GREEN = "\u001B[32m";
    final String ANSI_YELLOW = "\u001B[33m";
    final String ANSI_BLUE = "\u001B[34m";
    final String ANSI_PURPLE = "\u001B[35m";
    final String ANSI_CYAN = "\u001B[36m";
    final String ANSI_WHITE = "\u001B[37m";
    
    File inputFile = new File("suppliers.txt");
    File tempFile = new File("temp_suppliers.txt");
    boolean deleted = false;

    try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
         BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                if (fileSupplierId != supplierId) {
                    bw.write(String.join(",", parts));
                    bw.newLine();
                } else {
                    deleted = true;
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError deleting supplier file: " + e.getMessage() + ANSI_RESET);
    }

    if (!inputFile.delete()) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not delete original supplier file." + ANSI_RESET);
    }

    if (!tempFile.renameTo(inputFile)) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tCould not rename temporary supplier file."  + ANSI_RESET);
    }

    return deleted;
}

     public static void viewSuppliers() {
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

        try (BufferedReader br = new BufferedReader(new FileReader("suppliers.txt"))) {
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

private static String getSupplierName(int supplierId) {
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
            if (parts.length == 3) {
                int fileSupplierId = Integer.parseInt(parts[0]);
                if (fileSupplierId == supplierId) {
                    return parts[1]; // Return supplier name
                }
            }
        }
    } catch (IOException e) {
        System.out.println(ANSI_RED + "\t\t\t\t\t\tError loading supplier names: " + e.getMessage() + ANSI_RESET);
    }
    return "Unknown Supplier";
}


        }
