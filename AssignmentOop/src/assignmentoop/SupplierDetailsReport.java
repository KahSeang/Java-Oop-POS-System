
package assignmentoop;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class SupplierDetailsReport extends ReportGenerator {
    private static final String SUPPLIER_FILE_PATH = "suppliers.txt";

    public SupplierDetailsReport() {
        super("Supplier Details Report");
    }

    @Override
    public void generateReport() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("supplier_details_report.txt"))) {
            printHeader(bw);
            generateSupplierDetails(bw);
            printFooter(bw);
            System.out.println("\t\t\t\t\t\tSupplier Details Report generated successfully.");
        } catch (IOException e) {
            System.out.println("Error generating supplier details report: " + e.getMessage());
        }
    }

    private void generateSupplierDetails(BufferedWriter bw) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(SUPPLIER_FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    bw.write("Supplier ID: " + parts[0]);
                    bw.newLine();
                    bw.write("Supplier Name: " + parts[1]);
                    bw.newLine();
                    bw.write("Contact: " + parts[2]);
                    bw.newLine();
                    bw.write("---------------------------------");
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            bw.write("Error loading supplier details.");
            bw.newLine();
        }
    }
}
