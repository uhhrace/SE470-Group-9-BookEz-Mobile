import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Collection;

public class ROIManager{

    public static File output;//output.text file collects all information from PDFs
    public static Integer identifyer = 1;//for vectors id number 
    private static Integer nextEnd;//used to find desired strings 

    //HashMaps containing each orders information and paths 
    public static HashMap<Integer, orderObject> orders = new HashMap<Integer, orderObject>();
    public static HashMap<Integer, pathObject> paths = new HashMap<Integer, pathObject>();

    //objects contains the totals from each orders information
    private static totalObject totals = new totalObject(0.00, 0.00, 0.00,
    0.00, 0.00, 0.00);

    //user selecting files from device 
    public static void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection
        roiHeader();//writing in roi header to text file

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved

            File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

            for(File file : files){//itterate through collected files 

                readInSingleFile(file);//read in each file 
            }

            totalCollection();//adding to totals 
            addInfoToTable();//adding all collected information to output.text file
            addTotalsToTable();//sum up totals to table 
        }
    }//end of readInFiles

    //reads in a file and extracts the desired information 
    private static void readInSingleFile(File file){
        try{
            //getting path name and convering into a displayable method for user 
            String path = file.getAbsolutePath() + "\n";//collect path 
            int pathSegment = path.indexOf("Sales");//finds "Sales" segment of each order path
            path = identifyer + ": " + path.substring(pathSegment);//collects order path from "Sales" on
            paths.put(identifyer, new pathObject(identifyer, path));//adding paths to hash map
            
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
            PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
            PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
            String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

            orderCollector(docText, identifyer);//getting info from each pdf and adding to output.text file

            pdfDocument.close();//closing document
            fis.close();//closing file input stream
            identifyer++;
        } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    }//end of readInSingleFile function

    //used to extract the desired information from an ebay order reciept pdf and add the information into an order object in a hash map
    private static void orderCollector(String s, int id){

        //strings to collect information
        String orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
  
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = convertAndFind(s, "$", nextEnd, 0);
        int afterTotal = nextEnd;//grabs totals nextEnd value to look for a dollar sign after
        shipCost = convertAndFind(s, "Cost: ", nextEnd, 6);
        soldPrice = convertAndFind(s, "$", nextEnd, 0);
        shipPaid = convertAndFind(s, "$", nextEnd, 0);
        tax = convertAndFind(s, "$",nextEnd, 0);

        //no "Cost: " menas that shipping was not paid for and the order had a tracking number added instead
        if(s.indexOf("Cost:") == -1){
            shipCost = "$0.00";//set shipCost to 0
            nextEnd = afterTotal;//continue to search for the other values after total
            soldPrice = convertAndFind(s, "$", nextEnd, 0);
            shipPaid = convertAndFind(s, "$", nextEnd, 0);
            tax = convertAndFind(s, "$",nextEnd, 0);
        }

        //if sales taxes were not collected, set tax to 0
        if(s.indexOf("Sales tax (eBay collected)") == -1){
            tax = "$0.00";
        }
        
        //calculating profit after costs and if sales tax was collected 
        profitC = utility.profitCalc(total, shipCost, tax);      

        //adding extracted info into a orderObject
        orders.put(id, new orderObject(id, orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC));

    }//end of orderCollector

    ///////////////////////////////////////////////////////////////////////
    ///////////////Writing To Txt File Functions///////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //wiriting in header for each column and file formatting 
    public static void roiHeader(){
        output = new File("output.txt");//creating output.text file 

        try{
            FileWriter writer = new FileWriter(output);//writing in file 

            //header information for each column 
            writer.write("ROI Table\n");
            writer.write("Order Total\tSold For\tShip Charged\tShip Cost\tTaxes\tProfit\tOrder Number\t\n");
           
            writer.flush();
            writer.close();//closing writer 
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document used
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    }//end of ROI Header

    //adding each orders information into output.text file 
    protected static void addInfoToTable(){
        
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            Collection<orderObject> values = orders.values();//obtain all current orders 

            //add alt their information into the output file
            for(orderObject order : values){
                roiPanel.t.addRow(order);
                
                out.println(order.getTotal() + "\t" + order.getSoldPrice() + "\t" + order.getShipPaid() + "\t" + 
                order.getShipCost() + "\t" + order.getTax() + "\t" + order.getProfit() + "\t" + order.getOrderNum() + "\n");
            }

        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    
    }//end of creating ROI table 

    //adds totals of each row to the end of the file
    public static void addTotalsToTable(){
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            //adding in the information to file
            out.println("---------------------------------------------------------------------------------------");
            out.println("Totals from each row\n");
            out.println("$" + totals.getFinalTotal() + "\t$" + totals.getTotalSoldPrice() + "\t$" + totals.getTotalShipPaid() + "\t$" + 
            totals.getTotalShipCost() + "\t$" + totals.getTotalTax() + "\t$" + totals.getTotalProfit() + "\t" + "N/A");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        }
    }//end of add totals to table

    ///////////////////////////////////////////////////////////////////////
    /////////////////Total Collection Function////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //collects all information from pdf texts and continous to sum up each entered string 
    //utilizes boolean to determine if the total is being added or subtracted from current totals
    public static void totalCollection(){

        totals = new totalObject();//resets all fields to 0;

        Collection<orderObject> values = orders.values();

        for(orderObject order : values){

            //adding or deleting from current total collected
            totals.setFinalTotal(order.getTotal().substring(1));
            totals.setTotalShipCost(order.getShipCost().substring(1));
            totals.setTotalSoldPrice(order.getSoldPrice().substring(1));
            totals.setTotalShipPaid(order.getShipPaid().substring(1));
            totals.setTotalTax(order.getTax().substring(1));
            totals.setTotalProfit(order.getProfit().substring(1));
        }
    }//end of totalCollection

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Utility function/////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //finds a string segment and runs till the end of the line
    private static String convertAndFind(String docText, String id, int idInt, int mod){

        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find 

}//end of class