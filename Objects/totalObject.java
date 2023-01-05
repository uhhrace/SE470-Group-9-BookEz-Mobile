
//contains all totals from the collected information of ROIManager
public class totalObject {
    
    //for total collection
    private Double totalTotal = 0.00, totalShipCost = 0.00, totalSoldPrice = 0.00, 
    totalShipPaid = 0.00, totalTax = 0.00, totalProfit = 0.00;

    //consturctor for totalObject
    public totalObject(double totalTotal, double totalShipCost, double totalSoldPrice, 
    double totalShipPaid, double totalTax, double totalProfit){

        this.totalTotal = totalTotal;
        this.totalShipCost = totalShipCost;
        this.totalSoldPrice = totalSoldPrice;
        this.totalShipPaid = totalShipPaid;
        this.totalTax = totalTax;
        this.totalProfit = totalProfit;
    }//end of totalObject constructor

    //default constructor of totalObject
    public totalObject(){
        this.totalTotal = 0.00;
        this.totalShipCost = 0.00;
        this.totalSoldPrice = 0.00;
        this.totalShipPaid = 0.00;
        this.totalTax = 0.00;
        this.totalProfit = 0.00;
    }//end of default constructor

    //setter and getter methods
    //string s is used in all set functions as the holder of dollar amounts for said total
    public void setFinalTotal(String s){
        this.totalTotal = (double) Math.round((this.totalTotal + Double.parseDouble(s)) * 100) / 100;
    }

    public double getFinalTotal(){
        return totalTotal;
    }

    public void setTotalShipCost(String s){
        this.totalShipCost = (double) Math.round((this.totalShipCost + Double.parseDouble(s)) * 100) / 100;
    }

    public double getTotalShipCost(){
        return totalShipCost;
    }

    public void setTotalSoldPrice(String s){
        this.totalSoldPrice = (double) Math.round((this.totalSoldPrice + Double.parseDouble(s)) * 100) / 100;
    }

    public double getTotalSoldPrice(){
        return totalSoldPrice;
    }

    public void setTotalShipPaid(String s){
        this.totalShipPaid = (double) Math.round((this.totalShipPaid + Double.parseDouble(s)) * 100) / 100;
    }

    public double getTotalShipPaid(){
        return totalShipPaid;
    }

    public void setTotalTax(String s){
        this.totalTax = (double) Math.round((this.totalTax + Double.parseDouble(s)) * 100) / 100;
    }

    public double getTotalTax(){
        return totalTax;
    }

    public void setTotalProfit(String s){
        this.totalProfit = (double) Math.round((this.totalProfit + Double.parseDouble(s)) * 100) / 100;
    }

    public double getTotalProfit(){
        return totalProfit;
    } 
    
}//end of totalObject class
