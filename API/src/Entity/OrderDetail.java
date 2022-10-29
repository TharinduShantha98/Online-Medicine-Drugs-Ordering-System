package Entity;

public class OrderDetail {


    private String orderId;
    private String itemCode;
    private double Quantity;
    private  double price;

    public OrderDetail(String orderId, String itemCode, double quantity, double price) {
        this.orderId = orderId;
        this.itemCode = itemCode;
        Quantity = quantity;
        this.price = price;
    }

    public OrderDetail() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getQuantity() {
        return Quantity;
    }

    public void setQuantity(double quantity) {
        Quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}

