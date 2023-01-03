import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import javax.swing.JOptionPane;

public class listManager {
    public static File outputList;
    private static int index;

    //itterates through ROIManager.v vector containing all the order info, once the key is found it is deleted and all information is updated
    public static void searchAndRemove (String element){

        Collections.sort(ROIManager.v);//sorting vector before search

        try{//handling exceptions thrown from binary search 
            index = Collections.binarySearch(ROIManager.v, element, new StringComparator());//searching for element entered
        } catch(Exception e){
            System.out.println("Exception: " + e);
        }

        if(index >= 0){//element was found

            //subtracting from the totals collected 
            try{
                Class<?> c = ROIManager.class;
                Object o = c.getDeclaredConstructor().newInstance();

                //updating totals to be subtracted from what was first gathered
                Method m = ROIManager.class.getDeclaredMethod("totalCollection", String.class, boolean.class);
                m.setAccessible(true);
                m.invoke(o, ROIManager.v.get(index), false);
            } catch(Exception e){
                System.out.println("Exception: " + e);
            }

            //remove element from vectors
            ROIManager.v.remove(index);
            ROIManager.pathList.remove(index);

            //update each text file created
            updatePathList();
            updateROI(ROIManager.v);

        } else {//element was not found
            JOptionPane.showMessageDialog(null, "Element was not found");
        }
    }//end of searchAndRemove function

    //updates entire ROI table when called 
    private static void updateROI(Vector<String> v){
        try{
            //utilizing an instance of ROIManager to update each segment 
            Class<?> c = ROIManager.class;
            Object o = c.getDeclaredConstructor().newInstance();

            //resetting file to roi header
            Method m = ROIManager.class.getDeclaredMethod("roiHeader");
            m.setAccessible(true);
            m.invoke(o);

            //rewriting all order information into the text file
            for(int i = 0; i < v.size(); i++){

                Method m2 = ROIManager.class.getDeclaredMethod("outputWriter", String.class, boolean.class);
                m2.setAccessible(true);
                m2.invoke(o, v.get(i), false);
            }

            //rewriting all totals into the end of the text file
            Method m3 = ROIManager.class.getDeclaredMethod("addTotalsToTable");
            m3.setAccessible(true);
            m3.invoke(o);
            
        }catch(Exception ex){//catching exception thrown for invalid document inputs
            System.out.println("Exception thrown: " + ex);//printing error message 
        }
    }//end of update

    //will update the pathlist text file and display on screen when called
    private static void updatePathList(){
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
