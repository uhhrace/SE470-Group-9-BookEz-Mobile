import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.util.HashMap;
import java.util.Collection;

public class ROIManager{

    public static Integer identifyer = 1;//for vectors id number 
    private static Integer nextEnd;//used to find desired strings 

    //HashMaps containing each orders information and paths 
    public static HashMap<Integer, orderObject> orders = new HashMap<Integer, orderObject>();
    public static HashMap<Integer, pathObject> paths = new HashMap<Integer, pathObject>();

    //user selecting files from device 
    public static void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
            File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

            for(File file : files){//itterate through collected files 

                readInSingleFile(file);//read in each file 
            }
            addInfoToTable();//adding all collected information to output.text file
            
        }
    }//end of readInFiles

    //reads in a file and extracts the desired information 
    private static void readInSingleFile(File file){
        try{
            //getting path name and convering into a displayable method for user 
            String path = file.getAbsolutePath() + "\n";//collect path 
            int pathSegment = path.indexOf("Sales");//finds "Sales" segment of each order path
            path = identifyer + ": " + path.substring(pathSegment);//collects order path from "Sales" on

            pathTable.returnWriter().addRow(new pathObject(identifyer, path));

            //paths.put(identifyer, new pathObject(identifyer, path));//adding paths to hash map
            
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
        String orderNum;
        Double total, shipCost, soldPrice, shipPaid, tax, profitC;
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = Double.parseDouble(convertAndFind(s, "$", nextEnd, 1));
        int afterTotal = nextEnd;//grabs totals nextEnd value to look for a dollar sign after

        //no "Cost: " menas that shipping was not paid for and the order had a tracking number added instead
        if(s.indexOf("Cost:") == -1){
            shipCost = 0.00;//set shipCost to 0
            nextEnd = afterTotal;//continue to search for the other values after total
            soldPrice = Double.parseDouble(convertAndFind(s, "$", nextEnd, 1));
            shipPaid = Double.parseDouble(convertAndFind(s, "$", nextEnd, 1));
            //tax = Double.parseDouble(convertAndFind(s, "$",nextEnd, 1));
        } else {
            shipCost = Double.parseDouble(convertAndFind(s, "Cost: ", nextEnd, 7));
            soldPrice = Double.parseDouble(convertAndFind(s, "$", nextEnd, 1));
            shipPaid = Double.parseDouble(convertAndFind(s, "$", nextEnd, 1));
        }

         //if sales taxes were not collected, set tax to 0
         if(s.indexOf("Sales tax (eBay collected)") == -1){
            tax = 0.00;
        } else {
            tax = Double.parseDouble(convertAndFind(s, "$",nextEnd, 1));
        }
        
        //calculating profit after costs and if sales tax was collected 
        profitC = utility.profitCalc(total, shipCost, tax);      

        //adding extracted info into a orderObject
        orders.put(id, new orderObject(id, orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC));

    }//end of orderCollector

    //adding each orders information into output.text file 
    protected static void addInfoToTable(){
        Collection<orderObject> values = orders.values();//obtain all current orders 

        //add alt their information into the output file
        for(orderObject order : values){
            roiTable.returnWriter().addRow(order);
            
        }
        roiTable.returnWriter().addTotals();
    
    }//end of creating ROI table 

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