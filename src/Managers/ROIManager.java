package Managers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;

import Tables.roiTableWriter;
import Tables.roiTable;
import UI_Formatter.notification;
import Controller.controller;
import Objects.pathObject;
import Tables.pathTable;
import Objects.orderObject;

public class ROIManager{

    private static Integer identifyer = 1;//for files id number 
    private static Integer nextEnd;//used to find desired strings 
    private static roiTableWriter r = roiTable.returnWriter();//creating an instance of roi table writer

    /**
     * Reads in all files selected by the user from their local device
     */
    public static void readInFiles(){

        boolean valid = true;//used for error handling of invalid file types
        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
            File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

            for(int i = 0; valid && i < files.length; i++){
                valid = readInSingleFile(files[i]);//read in each file 

                    if(!valid){//exit loop once a false is found
                        break;
                    }
            }

            if(valid){
                r.addTotals();//finally adding final row of column totals to the roi table 
            }
        }
    }

    /**
     * Used to read in the list of dropped files into the upload panel
     */
    public static void readInDroppedFiles(List<File> files){
        boolean valid = true;//used for error handling of invalid file types

        for(int i = 0; valid && i < files.size(); i++){
            valid = readInSingleFile(files.get(i));//read in each file 

                if(!valid){//exit loop once a false is found
                    break;
                }
        }

        if(valid){
            r.addTotals();//finally adding final row of column totals to the roi table 
        }
    }

    /**
     * Reads in a single file. Convers and strips the order pdf into a string, then collects all of the orders information. 
     * Adding in the information into the roi table as it is collected. Returns true or false if the file type is valid
     * 
     * @param file Order reciept pdf to be processed 
     */
    private static boolean readInSingleFile(File file) {
        try {
            String path = file.getAbsolutePath() + "\n"; // collect path
            FileInputStream fis = new FileInputStream(file); // create new input stream
            PDDocument pdfDocument = PDDocument.load(fis); // load in pdf document
            PDFTextStripper pdfTextStripper = new PDFTextStripper(); // obtain text
            String docText = pdfTextStripper.getText(pdfDocument); // turning text into string
    
            // checks to ensure the order number can be found, else it is assumed the file is not valid
            int valid = docText.indexOf("Order number");
    
            if (valid == -1) {
                notification.showNotificationPopup(controller.getFrame(), "Incorrect File Format", false);
                pdfDocument.close(); // close the pdf document
                return false; // file is invalid
            } else {
                pathTable.returnWriter().addRow(new pathObject(identifyer, path)); // adding the row into the path table
                orderCollector(docText); // getting info from each pdf and adding to output.text file
                identifyer++;
                pdfDocument.close(); // close the pdf document
                return true; // file is valid
            }
    
        } catch (IOException ex) { // catching exception thrown for invalid document inputs
            System.out.println("File cannot be opened: " + ex); // printing error message
        }
        
        return false; // default to invalid file
    }

    /**
     * Colecting all of the information from an order reciept
     * @param doc the string of order information
     */
    private static void orderCollector(String doc){

        //strings to collect information
        String orderNum;
        Double total, shipCost, soldPrice, shipPaid, tax, profitC;
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(doc, "Order number ", nextEnd, 13);
        total = Double.parseDouble(convertAndFind(doc, "$", nextEnd, 1));
        int afterTotal = nextEnd;//grabs totals nextEnd value to look for a dollar sign after

        //no "Cost: " menas that shipping was not paid for and the order had a tracking number added instead
        if(doc.indexOf("Cost:") == -1){
            shipCost = 0.00;//set shipCost to 0
            nextEnd = afterTotal;//continue to search for the other values after total
            soldPrice = Double.parseDouble(convertAndFind(doc, "$", nextEnd, 1));
            shipPaid = Double.parseDouble(convertAndFind(doc, "$", nextEnd, 1));

        } else {
            shipCost = Double.parseDouble(convertAndFind(doc, "Cost: ", nextEnd, 7));
            soldPrice = Double.parseDouble(convertAndFind(doc, "$", nextEnd, 1));
            shipPaid = Double.parseDouble(convertAndFind(doc, "$", nextEnd, 1));

        }

        //if sales taxes were not collected, set tax to 0
        if(doc.indexOf("Sales tax (eBay collected)") == -1){
            tax = 0.00;

        } else {
            tax = Double.parseDouble(convertAndFind(doc, "$",nextEnd, 1));
        }
            
        //calculating profit after costs and if sales tax was collected 
        profitC = profitCalc(total, shipCost, tax);      

        //adding extracted info into an orderObject and adding that information into the roi table
        r.addRow(new orderObject(orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC));

    }

    /**
     * Converting and finding a specific segment of the uploaded pdf of an order reciept
     * @param docText all order information in string format 
     * @param id what is to be searched for 
     * @param idInt starting from 
     * @param mod should the string be moddified to be smaller 
     * @return string result
     */
    private static String convertAndFind(String docText, String id, int idInt, int mod){

        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    } 

    /**
     * Calculates profit from entered in double variables
     * @param total total order sold for
     * @param shipCost total shipping cost
     * @param tax tax paid
     * @return profit 
     */
    public static Double profitCalc(Double total, Double shipCost, Double tax){
        double profit = 0.0;//used for calculations
        profit = total - shipCost - tax;//calculating profit 
        profit = (double) Math.round(profit * 1000) / 1000.0;//rounding off two decimal places
        return profit;//returning profit obtained 

    }

    /**
     * Resetting ID when tables have no more rows of data
     */
    public static void resetID(){
        identifyer = 1;
    }
}