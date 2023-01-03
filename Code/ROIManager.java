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

public class ROIManager{

    public static File output;//output.text file collects all information from PDFs
    public static Vector<String> v = new Vector<String>();//stores all information collected from the PDFs
    public static Vector<String> pathList = new Vector<String>();//stores all the paths from each PDF uploaded
    //public static Vector<Double> totals = new Vector<Double>(5);
    private Integer nextEnd;//used to find desired strings 
    static Integer identifyer = 1;//for vectors id number 
    static Double totalTotal = 0.00, totalShipCost = 0.00, totalSoldPrice = 0.00, totalShipPaid = 0.00, totalTax = 0.00, totalProfit = 0.00;//for total collection
    
    //user selecting files from device 
    private void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection
        roiHeader();//writing in roi header to text file

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved

            //try{
                File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

                for(File file : files){//itterate through collected files 

                    /*//getting path name and convering into a displayable method for user 
                    String path = file.getAbsolutePath() + "\n";//collect path 
                    path = identifyer + ": " + convertAndFind(path, "Ebay Orders/", 0, 12) + "\n";//collecting file name selected by user
                    pathList.add(path);
                    //photoPanel.fileList.append(path);//displaying what file(s) were uploaded
                    
                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
                    PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                    String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                    outputWriter("ID: " + identifyer + "\n" + docText, true);//getting info from each pdf and adding to output.text file
                    totalCollection(docText, true);

                    pdfDocument.close();//closing document
                    fis.close();//closing file input stream
                    identifyer++;*/
                    readInSingleFile(file);
                }

                addTotalsToTable();
                
            //} catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
             //   System.out.println("File cannot be opened: " + ex);//printing error message 
            //} 
        }

    }//end of readInFiles

    private void readInSingleFile(File file){
        try{
            //getting path name and convering into a displayable method for user 
            String path = file.getAbsolutePath() + "\n";//collect path 
            path = identifyer + ": " + convertAndFind(path, "Ebay Orders/", 0, 12) + "\n";//collecting file name selected by user
            pathList.add(path);
            //photoPanel.fileList.append(path);//displaying what file(s) were uploaded
            
            FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
            PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
            PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
            String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

            outputWriter("ID: " + identifyer + "\n" + docText, true);//getting info from each pdf and adding to output.text file
            totalCollection(docText, true);

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
    protected void roiHeader(){
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

    //used to extract the desired information from an ebay order reciept pdf and add new info to output.text file
    //boolean used to determine wether the extracted information should be stored in the vector
    protected void outputWriter(String s, boolean vector){

        //strings to collect information
        String id, orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
  
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        id = convertAndFind(s, "ID: ", nextEnd, 4);
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = convertAndFind(s, "$", nextEnd, 0);
        shipCost = convertAndFind(s, "Cost: ", nextEnd, 6);
        soldPrice = convertAndFind(s, "$", nextEnd, 0);
        shipPaid = convertAndFind(s, "$", nextEnd, 0);
        tax = convertAndFind(s, "$",nextEnd, 0);

        if(vector == true){//ignoring this segment to keep taxes as is if being added into the vector 
            //if sales taxes were not collected, set tax to 0
            if(s.indexOf("Sales tax (eBay collected)") == -1){
                tax = "$0.00";
            }
        }

        //calculating profit after costs and if sales tax was collected 
        profitC = profitCalc(total, shipCost, tax);      

        //if info should be added to vector 
        if(vector == true){
            //adding extracted info into a vector of strings 
            v.add("ID: " + id + "\nOrder number " + orderNum + "\n" + total + "\nCost: " + shipCost + "\n" + soldPrice + "\n" + shipPaid + "\n" + tax + "\n");
        }

        //adding all collected information to output.text file
        addInfoToTable(orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC);

    }//end of outputWWriter

    //adding each files information into output.text file 
    private static void addInfoToTable(String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax, String profit){
        
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            //adding in the information to file 
            out.println(total + "\t" + soldPrice + "\t" + shipPaid + "\t" + shipCost + "\t" + tax + "\t" + profit + "\t" + orderNum + "\n");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    
    }//end of creating ROI table 

    //adds totals of each row to the end of the file
    private void addTotalsToTable(){
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            //adding in the information to file
            out.println("---------------------------------------------------------------------------------------");
            out.println("Totals from each row\n");
            out.println(getFinalTotal() + "\t" + getTotalSoldPrice() + "\t" + getTotalShipPaid() + "\t" + getTotalShipCost() + "\t" + getTotalTax() + "\t" + getTotalProfit() + "\t" + "N/A");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        }
    }//end of add totals to table

    ///////////////////////////////////////////////////////////////////////
    /////////////////Total Collection Functions////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //collects all information from pdf texts and continous to sum up each entered string 
    //utilizes boolean to determine if the total is being added or subtracted from current totals
    protected void totalCollection(String s, boolean add){

        //strings to collect information
        String total, shipCost, soldPrice, shipPaid, tax, profitC;
  
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        total = convertAndFind(s, "$", nextEnd, 0);
        shipCost = convertAndFind(s, "Cost: ", nextEnd, 6);
        soldPrice = convertAndFind(s, "$", nextEnd, 1);
        shipPaid = convertAndFind(s, "$", nextEnd, 1);
        tax = convertAndFind(s, "$",nextEnd, 1);

        if(add == true){//if adding into from originally collected string of pdf and tax was not charged check
            //if sales taxes were not collected, set tax to 0
            if(s.indexOf("Sales tax (eBay collected)") == -1){
                tax = "$0.00";
            }
        }

        //calculating profit after costs and if sales tax was collected 
        profitC = profitCalc(total, shipCost, tax); 

        //removing '$' from strings
        total = total.substring(1);
        shipCost = shipCost.substring(1);
        tax = tax.substring(1);
        profitC = profitC.substring(1);

        //adding or deleting from current total collected
        setFinalTotal(total, add);
        setTotalShipCost(shipCost, add);
        setTotalSoldPrice(soldPrice, add);
        setTotalShipPaid(shipPaid, add);
        setTotalTax(tax, add);
        setTotalProfit(profitC, add);
       
    }//end of totalCollection


    //string s is used in all set functions as the holder of dollar amounts for said total
    //add is used to determine wether we are adding to the current totals or deleting 
    //if we are deleting from a total we have to use ROIManager.NAME in order to delet as it is dome from listManager
    public void setFinalTotal(String s, boolean add){
        if(add){
            totalTotal = (double) Math.round((totalTotal + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalTotal = (double) Math.round((ROIManager.totalTotal - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getFinalTotal(){
        return totalTotal;
    }

    public void setTotalShipCost(String s, boolean add){
        if(add){
            totalShipCost = (double) Math.round((totalShipCost + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalShipCost = (double) Math.round((ROIManager.totalShipCost - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getTotalShipCost(){
        return totalShipCost;
    }

    public void setTotalSoldPrice(String s, boolean add){
        if(add){
            totalSoldPrice = (double) Math.round((totalSoldPrice + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalSoldPrice = (double) Math.round((ROIManager.totalSoldPrice - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getTotalSoldPrice(){
        return totalSoldPrice;
    }

    public void setTotalShipPaid(String s, boolean add){
        if(add){
            totalShipPaid = (double) Math.round((totalShipPaid + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalShipPaid = (double) Math.round((ROIManager.totalShipPaid - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getTotalShipPaid(){
        return totalShipPaid;
    }

    public void setTotalTax(String s, boolean add){
        if(add){
            totalTax = (double) Math.round((totalTax + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalTax = (double) Math.round((ROIManager.totalTax - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getTotalTax(){
        return totalTax;
    }

    public void setTotalProfit(String s, boolean add){
        if(add){
            totalProfit = (double) Math.round((totalProfit + Double.parseDouble(s)) * 100) / 100;
        } else {
            ROIManager.totalProfit = (double) Math.round((ROIManager.totalProfit - Double.parseDouble(s)) * 100) / 100;
        }
    }

    public double getTotalProfit(){
        return totalProfit;
    } 

    ///////////////////////////////////////////////////////////////////////
    /////////////////////////Utility functions/////////////////////////////
    ///////////////////////////////////////////////////////////////////////

    //finds a string segment and runs till the end of the line
    String convertAndFind(String docText, String id, int idInt, int mod){

        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find 

    //calculates profit
    String profitCalc(String total, String shipCost, String tax){

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

