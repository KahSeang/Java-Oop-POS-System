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

class SummaryReport extends ReportGenerator {
    private static final String ITEM_FILE_PATH = "items.txt";
    private static final String ORDER_HISTORY_FILE_PATH = "order_history.txt";

    public SummaryReport() {
        super("Summary Report");
    }

    @Override
    public void generateReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("summary_report.txt"))) {
            printHeader(bw);
            generateSummary(bw);
            printFooter(bw);
            System.out.println("\t\t\t\t\t\tSummary Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating summary report: " + e.getMessage());
        }
    }

    private void generateSummary(BufferedWriter bw) throws IOException {
        double totalValue = 0.0;
        int totalItems = 0;
        int totalOrders = 0;

        // Calculate total value and items
        try (BufferedReader br = new BufferedReader(new FileReader(ITEM_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    int quantity = Integer.parseInt(parts[3]);
                    double price = Double.parseDouble(parts[4]);
                    totalValue += quantity * price;
                    totalItems += quantity;
                }
            }
        } catch (IOException e) {
            bw.write("Error loading inventory data for summary.");
            bw.newLine();
        }

        // Calculate total orders
        try (BufferedReader br = new BufferedReader(new FileReader(ORDER_HISTORY_FILE_PATH))) {
            while (br.readLine() != null) {
                totalOrders++;
            }
        } catch (IOException e) {
            bw.write("Error loading order history for summary.");
            bw.newLine();
        }

        // Write summary report
        bw.write("Total Inventory Value: RM " + String.format("%.2f", totalValue));
        bw.newLine();
        bw.write("Total Number of Items: " + totalItems);
        bw.newLine();
        bw.write("Total Number of Orders: " + totalOrders);
        bw.newLine();
        bw.write("---------------------------------");
        bw.newLine();
    }
}
