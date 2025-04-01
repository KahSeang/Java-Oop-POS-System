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

class OrderHistoryReport extends ReportGenerator {
    private static final String ORDER_HISTORY_FILE_PATH = "order_history.txt";

    public OrderHistoryReport() {
        super("Orders History Report");
    }

    @Override
    public void generateReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("order_history_report.txt"))) {
            printHeader(bw);
            generateOrderHistoryReport(bw);
            printFooter(bw);
            System.out.println("\t\t\t\t\t\tOrder History Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating order history report: " + e.getMessage());
        }
    }

    private void generateOrderHistoryReport(BufferedWriter bw) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(ORDER_HISTORY_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    bw.write("Order ID: " + parts[0]);
                    bw.newLine();
                    bw.write("Supplier ID: " + parts[1]);
                    bw.newLine();
                    bw.write("Item ID: " + parts[2]);
                    bw.newLine();
                    bw.write("Quantity: " + parts[3]);
                    bw.newLine();
                    bw.write("Date: " + parts[4]);
                    bw.newLine();
                    bw.write("Total Amount: RM " + parts[5]);
                    bw.newLine();
                    bw.write("---------------------------------");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            bw.write("Error loading order history.");
            bw.newLine();
        }
    }
}
