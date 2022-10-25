package Entity;

import java.sql.Date;

public class Item {
    private  String itemCode;
    private  String itemName;
    private  String brandName;
    private  String description;
    private double price;
    private String drugType;
    private double quantity;
    private Date expireDate;


    public Item(String itemCode, String itemName, String brandName, String description,
                double price, String drugType, double quantity, Date expireDate) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.brandName = brandName;
        this.description = description;
        this.price = price;
        this.drugType = drugType;
        this.quantity = quantity;
        this.expireDate = expireDate;
    }

    public Item() {
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
