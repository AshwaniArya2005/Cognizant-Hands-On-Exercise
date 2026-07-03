interface Document {
    void open();
    void save();
    void close();
}

class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document (.docx)...");
    }
    @Override
    public void save() {
        System.out.println("Saving changes to Word Document.");
    }
    @Override
    public void close() {
        System.out.println("Closing Word Document.");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF Document (.pdf) in reader mode...");
    }
    @Override
    public void save() {
        System.out.println("Saving annotations to PDF Document.");
    }
    @Override
    public void close() {
        System.out.println("Closing PDF Document.");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Spreadsheet (.xlsx)...");
    }
    @Override
    public void save() {
        System.out.println("Recalculating formulas and saving Excel Spreadsheet.");
    }
    @Override
    public void close() {
        System.out.println("Closing Excel Spreadsheet.");
    }
}

abstract class DocumentFactory {
    public abstract Document createDocument();
    public void manageDocument() {
        Document doc = createDocument();
        doc.open();
        doc.save();
        doc.close();
    }
}

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

public class FactoryMethodPatternDemo {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Implementation Demo ===");
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        System.out.println("\n--- Processing Word Document ---");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.save();
        wordDoc.close();

        System.out.println("\n--- Processing PDF Document ---");
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.save();
        pdfDoc.close();

        System.out.println("\n--- Processing Excel Document ---");
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.save();
        excelDoc.close();

        System.out.println("\n=============================================");
        System.out.println("SUCCESS: Factory Method pattern working correctly!");
        System.out.println("=============================================");
    }
}
