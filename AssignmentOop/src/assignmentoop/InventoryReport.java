/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentoop;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class InventoryReport extends ReportGenerator {
    private static final String ITEM_FILE_PATH = "items.txt";

    public InventoryReport() {
        super("Inventory Stock Levels Report");
    }

    @Override
    public void generateReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory_report.txt"))) {
            printHeader(bw);
            generateInventoryStockLevelReport(bw);
            printFooter(bw);
            System.out.println("\t\t\t\t\t\tInventory Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating inventory report: " + e.getMessage());
        }
    }

    private void generateInventoryStockLevelReport(BufferedWriter bw) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    bw.write("Item ID: " + parts[0]);
                    bw.newLine();
                    bw.write("Item Name: " + parts[1]);
                    bw.newLine();
                    bw.write("Category: " + parts[2]);
                    bw.newLine();
                    bw.write("Quantity: " + parts[3]);
                    bw.newLine();
                    bw.write("Price: " + parts[4]);
                    bw.newLine();
                    bw.write("---------------------------------");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            bw.write("Error loading inventory data.");
            bw.newLine();
        }
    }
}
