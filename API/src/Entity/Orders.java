package Entity;

import javax.sql.DataSource;
import java.sql.Date;
import java.util.ArrayList;

public class Orders {
    private String orderId;
    private Date orderDate;
    private double orderTotal;
    private String patientId;
    private ArrayList<OrderDetail> orderDetails;


    public Orders(String orderId, Date orderDate, double orderTotal, String patientId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.patientId = patientId;
    }

    public Orders(String orderId, Date orderDate, double orderTotal, String patientId, ArrayList<OrderDetail> orderDetails) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
        this.patientId = patientId;
        this.orderDetails = orderDetails;
    }

    public Orders() {
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public ArrayList<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(ArrayList<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }
}
