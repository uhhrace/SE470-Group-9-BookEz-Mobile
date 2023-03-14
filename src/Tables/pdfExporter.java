package Tables;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;

public class pdfExporter{
    
    public static void createPDF(JTable table) throws Exception {
        //creating a file chooser dialog to let the user select the destination file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save PDF File");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        int userSelection = fileChooser.showSaveDialog(null);
        if(userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            String DEST = fileToSave.getAbsolutePath();
            if (!DEST.endsWith(".pdf")) {
                DEST += ".pdf";
            }

            PdfWriter writer = new PdfWriter(new FileOutputStream(DEST));
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document doc = new Document(pdfDoc);

            //adding a structure format to the document
            doc.add(new Paragraph("BookEz").setTextAlignment(TextAlignment.CENTER).setFontSize(20));
            doc.add(new Paragraph("Bookkeeping made easy").setTextAlignment(TextAlignment.CENTER).setFontSize(15));
            doc.add(new Paragraph("ROI Table From: " + LocalDate.now().toString()).setTextAlignment(TextAlignment.LEFT).setFontSize(12));
            
            //creating a table for the pdf (modified column length to exclude the checkbox)
            Table pdfTable = new Table(UnitValue.createPercentArray(table.getColumnCount() - 1)).useAllAvailableWidth();

            //adding headers
            for (int j = 0; j < table.getColumnCount() - 1; j++) {
                Cell headerCell = new Cell();
                Paragraph headerParagraph = new Paragraph(table.getColumnName(j));
                headerCell.add(headerParagraph);
                pdfTable.addHeaderCell(headerCell);
            }
    
            //adding data rows
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount() - 1; j++) {

                    Cell cell = new Cell();
                    Paragraph paragraph = new Paragraph(String.valueOf(table.getValueAt(i, j)));
                    cell.add(paragraph);
                    pdfTable.addCell(cell);

                }
            }

            doc.add(pdfTable);
            doc.close();
            //open the PDF file after export
            Desktop.getDesktop().open(new File(DEST));
        }
    }
}