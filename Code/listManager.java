import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JOptionPane;

public class listManager {
    public static File outputList;

    //itterates through ROIManager.v vector containing all the order info, once the key is found it is deleted and all information is updated
    public static void searchAndRemove (String element){

        int elem = Integer.parseInt(element);//converts desired element to be deleted into an integer

        if(ROIManager.orders.containsKey(elem)){//checking if element to be removed is in te hash map

            orderObject deleteOrder = ROIManager.orders.get(elem);//locates order with matching key 
            ROIManager.orders.remove(deleteOrder.getID());//removes order from hash map

            ROIManager.totalCollection();//subtracting from the totals collected 

            ///////////issue deletes correct field but after the fields are moved over and 2 now becomes 0 in the pathList vector
            System.out.println("Element id field: " + (deleteOrder.getID() - 1));
            ROIManager.pathList.remove(deleteOrder.getID() - 1);//remove element from pathList

            //update each text file created
            updatePathList();
            updateROI(ROIManager.v);
        } else {//element was not found
            JOptionPane.showMessageDialog(null, "Element was not found");
        }
    }//end of searchAndRemove function

    //updates entire ROI table when called 
    private static void updateROI(Vector<String> v){
       
        //rewriting header
        ROIManager.roiHeader();

        //rewriting all order information into the text file
        ROIManager.addInfoToTable();

        //rewriting all totals into the end of the text file
        ROIManager.addTotalsToTable();
            
       
    }//end of update

    //will update the pathlist text file and display on screen when called
    static void updatePathList(){
        outputList = new File("pathList.txt");//getting text file

        try{
            FileWriter writer = new FileWriter(outputList);//rewriting the file 

            for(int i = 0; i < ROIManager.pathList.size(); i++){//writing all paths into the file 
                writer.write(ROIManager.pathList.get(i));
            }

            //closing writer 
            writer.flush();
            writer.close();

            //displaying pathlist
            FileReader reader = new FileReader(outputList.getPath());
            BufferedReader br = new BufferedReader(reader);
            photoPanel.fileList.read( br, null );
            br.close();
            photoPanel.fileList.requestFocus();

        } catch(Exception e){//catchign exceptions thrown 
            System.out.println("Exception: " + e);//printing error message 
        }

    }//end of update path list 

}//end of listManager class 

///////////////////////////////////////////////////////////////////////
///////////////////////////Utility Class///////////////////////////////
///////////////////////////////////////////////////////////////////////

//class utilized for itterating through vector and comparing its ID to the one to be deleted
class StringComparator implements Comparator<String>{
    @Override
    public int compare(String s1, String s2){//function preformes the comparisons
        int id1 = extractID(s1);//obtains id from vector value 
        int id2 = Integer.parseInt(s2);//converts id to be found into comparable int value 

        return Integer.compare(id1, id2);//compares values 
    }

    //utility function that serves to extract the id from each element in the vector and convert it to an int
    private int extractID(String s){

        //implements fail cases if ID: is not found 
        int idStart = s.indexOf("ID: ") + 4;
        if(idStart < 4){
            return 0;
        }
        int idEnd = s.indexOf("\n", idStart);
        if(idEnd < 0){
            return s.length();
        }

        //obtains correct positions and converts string to int as a return 
        return Integer.parseInt(s.substring(idStart, idEnd));
    }
}
