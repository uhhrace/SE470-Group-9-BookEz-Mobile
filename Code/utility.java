
//houses a small utility function is used in ROIManager
public class utility {

    //calculates profit
    public static Double profitCalc(Double total, Double shipCost, Double tax){

        double profit = 0.0;//used for calculations
      
        profit = total - shipCost - tax;//calculating profit 
        profit = (double) Math.round(profit * 1000) / 1000.0;//rounding off two decimal places

        return profit;//returning profit obtained 

    }//end of profit calculation
}
