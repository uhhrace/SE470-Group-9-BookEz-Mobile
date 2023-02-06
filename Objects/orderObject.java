public class orderObject {

    private int id;
    private String orderNum;
    Double total, shipCost, soldPrice, shipPaid, tax, profit;

    //consturctor for object text
    public orderObject(int id, String orderNum, Double total, Double shipCost, Double soldPrice, Double shipPaid, Double tax, Double profit){
        
        this.id = id;
        this.orderNum = orderNum;
        this.total = total;
        this.shipCost = shipCost;
        this.soldPrice = soldPrice;
        this.shipPaid = shipPaid;
        this.tax = tax;
        this.profit = profit;
    }//end of orderObject constructor

    //setter and getter methods
    public void setID(int id){
        this.id = id;
    }

    public int getID(){
        return id;
    }

    public void setOrderNum(String orderNum){
        this.orderNum = orderNum;
    }

    public String getOrderNum(){
        return orderNum;
    }

    public void setTotal(Double total){
        this.total = total;
    }

    public Double getTotal(){
        return total;
    }

    public void setShipCost(Double shipCost){
        this.shipCost = shipCost;
    }

    public Double getShipCost(){
        return shipCost;
    }

    public void setSoldPrice(Double soldPrice){
        this.soldPrice = soldPrice;
    }

    public Double getSoldPrice(){
        return soldPrice;
    }

    public void setShipPaid(Double shipPaid){
        this.shipPaid = shipPaid;
    }

    public Double getShipPaid(){
        return shipPaid;
    }

    public void setTax(Double tax){
        this.tax = tax;
    }

    public Double getTax(){
        return tax;
    }

    public void setProfit(Double profit){
        this.profit = profit;
    }

    public Double getProfit(){
        return profit;
    }

}//end of orderObject class
