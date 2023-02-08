public class orderObject {

    private String orderNum;
    Double total, shipCost, soldPrice, shipPaid, tax, profit;

    /**
     * Constructor for order object
     * @param orderNum order number
     * @param total total sold price
     * @param shipCost shipping cost 
     * @param soldPrice item sold price 
     * @param shipPaid shipping paid by buyer
     * @param tax taxes paid 
     * @param profit profit made
     */
    public orderObject(String orderNum, Double total, Double shipCost, Double soldPrice, Double shipPaid, Double tax, Double profit){
  
        this.orderNum = orderNum;
        this.total = total;
        this.shipCost = shipCost;
        this.soldPrice = soldPrice;
        this.shipPaid = shipPaid;
        this.tax = tax;
        this.profit = profit;
    }

    //setter and getter methods
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
}
