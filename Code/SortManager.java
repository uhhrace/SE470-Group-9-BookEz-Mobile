import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class sortManager{

    //sorting by profit made in either ascending or descending order
    //boolean descides the order to be sorted in
    public static void profitSort(boolean ascending){

        //converting hashmap of orderObjects into a list
        List<orderObject> list = new ArrayList<>(ROIManager.orders.values());

        //sorting the entire hash map of order objects based on profit 
        Collections.sort(list, new Comparator<orderObject>(){
            public int compare(orderObject o1, orderObject o2){

                //remove '$' from each profit and convert the string into a double for comparision
                Double first = o1.getProfit();
                Double second = o2.getProfit();
                if(ascending){
                    return Double.compare(second, first);
                } else {
                    return Double.compare(first, second);
                }
            }
        });

        //ROIManager.roiHeader();//rewriting header of the ROI table

        //rewriting all the information in a sorted order 
        for(orderObject order : list){
            addToTable(order);
        }

        ROIManager.addTotalsToTable();//rewriting totals to ROI table

    }//end of highest profit sort

    //adds single order information into the ROI table
    private static void addToTable(orderObject order){
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {

            //add all order information into the output file
            out.println(order.getTotal() + "\t" + order.getSoldPrice() + "\t" + order.getShipPaid() + "\t" + 
            order.getShipCost() + "\t" + order.getTax() + "\t" + order.getProfit() + "\t" + order.getOrderNum() + "\n");

        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    }//end of addToTable

}//end of Sort Manager