package com.example.vmApp.model;



import javax.persistence.*;
@Entity
@Table(name="Item")
public class item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idItem")
    private int idItem;
    @Column(name = "itemCode")
    private String itemCode;
    @Column(name = "itemName")
    private String itemName;
    @Column(name = "Quantity")
    private int quantity;
    @Column(name = "itemCost")
    private double itemCost;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public item(String itemCode, String itemName, double itemCost, int idItem, int quantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemCost = itemCost;
        this.idItem = idItem;
        this.quantity = quantity;
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

    public double getItemCost() {
        return itemCost;
    }

    public void setItemCost(double itemCost) {

        this.itemCost = itemCost;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", itemCost=" + itemCost +
                '}';
    }

    public item() {

    }

}
