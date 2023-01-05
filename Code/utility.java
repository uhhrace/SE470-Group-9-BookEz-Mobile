
//houses a small utility function is used in ROIManager
public class utility {

    //calculates profit
    public static String profitCalc(String total, String shipCost, String tax){

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
}
