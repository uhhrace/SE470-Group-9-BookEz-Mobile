import java.awt.Color;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.TransferHandler;

public class dropBox{
    private JLabel label;
    private JTextArea textArea;

    public JPanel dropBoxArea() {
        JPanel dropArea = new JPanel();
        //dropArea.setLayout(new BorderLayout());
        
        label = new JLabel("Drag a PDF file here");
        dropArea.add(label);

        textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        dropArea.add(textArea);

        JPanel dropPanel = new JPanel();
        dropPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        dropPanel.setTransferHandler(new PDFTransferHandler());
        dropArea.add(dropPanel);

       return dropArea;
    }

    class PDFTransferHandler extends TransferHandler {
        private DataFlavor pdfFlavor = new DataFlavor("application/pdf", "PDF Files");

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            if (!support.isDataFlavorSupported(pdfFlavor)) {
                return false;
            }

            return true;
        }

        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            Transferable transferable = support.getTransferable();
            try {
                @SuppressWarnings("unchecked")
                List<File> files = (List<File>) transferable.getTransferData(pdfFlavor);

                for (File file : files) {
                    if (!file.getName().endsWith(".pdf")) {
                        /*
                        JOptionPane.showMessageDialog(dropBox.this,
                                "File is not a PDF file. Please only drag PDF files.",
                                "Invalid File Type", JOptionPane.ERROR_MESSAGE);
                        */
                        System.out.println("Invalid file");
                        return false;
                    }
                    System.out.println("Here");
                    textArea.append(file.getName() + "\n");
                }
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }
}




