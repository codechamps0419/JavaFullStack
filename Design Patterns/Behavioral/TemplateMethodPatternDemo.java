/*
@Author: Dynamic Duo Learners

REAL WORLD EXAMPLE:
Generating reports in different formats.

Every report follows the same overall workflow:
1. Fetch data
2. Validate data
3. Format report
4. Export report
5. Audit

Some steps are common; some vary by report type.

WITHOUT TEMPLATE METHOD PATTERN:
PdfReportGenerator and CsvReportGenerator may implement the complete
report workflow separately.

Problems:
- Common steps such as fetching and validation get duplicated.
- The same workflow sequence is repeated in multiple classes.
- Changes to common steps must be updated in every report class.
- Different implementations may accidentally follow different step orders.
- Code becomes harder to maintain as more report types are added.

WITH TEMPLATE METHOD PATTERN:
The base class defines the fixed workflow once.
Subclasses override only the steps that are different for each
report type.

MAIN IDEA:
"Define the algorithm skeleton in a base class and let subclasses
 customize selected steps without changing the sequence."

STRUCTURE:

ReportGenerator (Abstract Class)
        |
        +--> PdfReportGenerator
        +--> CsvReportGenerator

*/

public class TemplateMethodPatternDemo {

    public static void main(String[] args) {
        System.out.println("====== REPORTS USING TEMPLATE METHOD ======\n");

        ReportGenerator pdf = new PdfReportGenerator();
        pdf.generateReport();

        System.out.println();

        ReportGenerator csv = new CsvReportGenerator();
        csv.generateReport();
    }
}

// 1. ABSTRACT CLASS
abstract class ReportGenerator {

    // TEMPLATE METHOD: fixed algorithm sequence
    public final void generateReport() {
        fetchData();
        validateData();
        formatReport();
        exportReport();
        afterExport();
    }

    // Common fixed step
    private void fetchData() {
        System.out.println("1. Fetching report data from database...");
    }

    // Common fixed step
    private void validateData() {
        System.out.println("2. Validating report data...");
    }

    // Required variable step
    protected abstract void formatReport();

    // Required variable step
    protected abstract void exportReport();

    // Hook: optional override
    protected void afterExport() {
        System.out.println("5. Common audit log saved.");
    }
}

// 2. CONCRETE CLASS
class PdfReportGenerator extends ReportGenerator {
    @Override
    protected void formatReport() {
        System.out.println("3. Formatting data as PDF.");
    }

    @Override
    protected void exportReport() {
        System.out.println("4. Exporting report.pdf");
    }
}

// 3. CONCRETE CLASS
class CsvReportGenerator extends ReportGenerator {
    @Override
    protected void formatReport() {
        System.out.println("3. Formatting data as CSV.");
    }

    @Override
    protected void exportReport() {
        System.out.println("4. Exporting report.csv");
    }

    @Override
    protected void afterExport() {
        System.out.println("5. CSV-specific audit metadata saved.");
    }
}

/*
Output:

====== REPORTS USING TEMPLATE METHOD ======

1. Fetching report data from database...
2. Validating report data...
3. Formatting data as PDF.
4. Exporting report.pdf
5. Common audit log saved.

1. Fetching report data from database...
2. Validating report data...
3. Formatting data as CSV.
4. Exporting report.csv
5. CSV-specific audit metadata saved.

*/
