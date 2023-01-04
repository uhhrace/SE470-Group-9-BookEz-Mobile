import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.HashMap;
import java.util.Collection;

public class ROIManager{

    public static File output;//output.text file collects all information from PDFs
    public static Vector<String> v = new Vector<String>();//stores all information collected from the PDFs
    public static Vector<String> pathList = new Vector<String>();//stores all the paths from each PDF uploaded
    private static Integer nextEnd;//used to find desired strings 
    static Integer identifyer = 1;//for vectors id number 

    static Double totalTotal = 0.00, totalShipCost = 0.00, totalSoldPrice = 0.00, 
    totalShipPaid = 0.00, totalTax = 0.00, totalProfit = 0.00;//for total collection

    static HashMap<Integer, orderObject> orders = new HashMap<Integer, orderObject>();

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

    private static void readInSingleFile(File file){
        try{
            //getting path name and convering into a displayable method for user 
            String path = file.getAbsolutePath() + "\n";//collect path 
            path = identifyer + ": " + convertAndFind(path, "Ebay Orders/", 0, 12) + "\n";//collecting file name selected by user
            pathList.add(path);
            
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
    }

    ///////////////////////////////////////////////////////////////////////
    ///////////////Writing To Txt File Functions///////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //wiriting in header for each column and file formatting 
    protected static void roiHeader(){
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

    //used to extract the desired information from an ebay order reciept pdf and add the information into an order object in a hash map
    protected static void orderCollector(String s, int id){

        //strings to collect information
        String orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
  
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = convertAndFind(s, "$", nextEnd, 0);
        shipCost = convertAndFind(s, "Cost: ", nextEnd, 6);
        soldPrice = convertAndFind(s, "$", nextEnd, 0);
        shipPaid = convertAndFind(s, "$", nextEnd, 0);
        tax = convertAndFind(s, "$",nextEnd, 0);

        //if sales taxes were not collected, set tax to 0
        if(s.indexOf("Sales tax (eBay collected)") == -1){
            tax = "$0.00";
        }
        
        //calculating profit after costs and if sales tax was collected 
        profitC = profitCalc(total, shipCost, tax);      

        //adding extracted info into a orderObject
        orders.put(id, new orderObject(id, orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC));

    }//end of orderCollector

    //adding each orders information into output.text file 
    protected static void addInfoToTable(){
        
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            Collection<orderObject> values = orders.values();//obtain all current orders 

            //add al their information into the output file
            for(orderObject order : values){
                out.println(order.getTotal() + "\t" + order.getSoldPrice() + "\t" + order.getShipPaid() + "\t" + 
                order.getShipCost() + "\t" + order.getTax() + "\t" + order.getProfit() + "\t" + order.getOrderNum() + "\n");
            }

        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    
    }//end of creating ROI table 

    //adds totals of each row to the end of the file
    static void addTotalsToTable(){
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            //adding in the information to file
            out.println("---------------------------------------------------------------------------------------");
            out.println("Totals from each row\n");
            out.println(getFinalTotal() + "\t" + getTotalSoldPrice() + "\t" + getTotalShipPaid() + "\t" + 
            getTotalShipCost() + "\t" + getTotalTax() + "\t" + getTotalProfit() + "\t" + "N/A");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        }
    }//end of add totals to table

    ///////////////////////////////////////////////////////////////////////
    /////////////////Total Collection Functions////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //collects all information from pdf texts and continous to sum up each entered string 
    //utilizes boolean to determine if the total is being added or subtracted from current totals
    protected static void totalCollection(){

        totalTotal = 0.00;
        totalShipCost = 0.00;
        totalSoldPrice = 0.00;
        totalShipPaid = 0.00;
        totalTax = 0.00; 
        totalProfit = 0.00;

        Collection<orderObject> values = orders.values();

        for(orderObject order : values){

            //adding or deleting from current total collected
            setFinalTotal(order.getTotal().substring(1));
            setTotalShipCost(order.getShipCost().substring(1));
            setTotalSoldPrice(order.getSoldPrice().substring(1));
            setTotalShipPaid(order.getShipPaid().substring(1));
            setTotalTax(order.getTax().substring(1));
            setTotalProfit(order.getProfit().substring(1));
        }
    }//end of totalCollection


    //string s is used in all set functions as the holder of dollar amounts for said total
    //add is used to determine wether we are adding to the current totals or deleting 
    //if we are deleting from a total we have to use ROIManager.NAME in order to delet as it is dome from listManager
    public static void setFinalTotal(String s){
        totalTotal = (double) Math.round((totalTotal + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getFinalTotal(){
        return totalTotal;
    }

    public static void setTotalShipCost(String s){
        totalShipCost = (double) Math.round((totalShipCost + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getTotalShipCost(){
        return totalShipCost;
    }

    public static void setTotalSoldPrice(String s){
        totalSoldPrice = (double) Math.round((totalSoldPrice + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getTotalSoldPrice(){
        return totalSoldPrice;
    }

    public static void setTotalShipPaid(String s){
        totalShipPaid = (double) Math.round((totalShipPaid + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getTotalShipPaid(){
        return totalShipPaid;
    }

    public static void setTotalTax(String s){
        totalTax = (double) Math.round((totalTax + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getTotalTax(){
        return totalTax;
    }

    public static void setTotalProfit(String s){
        totalProfit = (double) Math.round((totalProfit + Double.parseDouble(s)) * 100) / 100;
    }

    public static double getTotalProfit(){
        return totalProfit;
    } 

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Utility functions/////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //finds a string segment and runs till the end of the line
    static String convertAndFind(String docText, String id, int idInt, int mod){

        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find 

    //calculates profit
    static String profitCalc(String total, String shipCost, String tax){

        double profit = 0.0;//used for calculations
        
        //turning each string into a double for calculations 
        double t = Double.parseDouble(total.substring(1));
        double sC = Double.parseDouble(shipCost.substring(1));
        double ta = Double.parseDouble(tax.substring(1));
        
        profit = t - sC - ta;//calculating profit 
        
        profit = (double) Math.round(profit * 100) / 100;//rounding off two decimal places
        String profitString = "$" + profit + "";//changing to string and adding $ for formatting

        //checking of profit has two place values for the cents 
        if(profitString.length() == 5){
            profitString += "0";//appending 0 if has one 
        }

        return profitString;//returning profit obtained 

    }//end of profit calculation

}//end of class

