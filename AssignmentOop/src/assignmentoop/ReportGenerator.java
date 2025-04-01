
package assignmentoop;

import java.io.BufferedWriter;
import java.io.IOException;

abstract class ReportGenerator {
    protected String reportName;

    public ReportGenerator(String reportName) {
        this.reportName = reportName;
    }

    // Abstract method that must be implemented by all subclasses
    public abstract void generateReport();

    // print the report header
    protected void printHeader(BufferedWriter bw) throws IOException {
        bw.write("=================================");
        bw.newLine();
        bw.write("          " + reportName);
        bw.newLine();
        bw.write("=================================");
        bw.newLine();
        bw.newLine();
    }

    //  print a footer (can be overridden by subclasses)
    protected void printFooter(BufferedWriter bw) throws IOException {
        bw.newLine();
        bw.write("=================================");
        bw.newLine();
        bw.write("        End of " + reportName);
        bw.newLine();
        bw.write("=================================");
        bw.newLine();
    }
}
