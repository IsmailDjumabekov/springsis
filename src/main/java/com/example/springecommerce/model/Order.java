package com.example.springecommerce.model;

import java.util.Date;

public class Order {
    private Integer id;
    private String name;
    private Date dateCreation;
    private Date dateReceived;
    private double total;

    public Order(){

    }

    public Order(Integer id, String name, Date dateCreation, Date dateReceived, double total) {
        super();
        this.id = id;
        this.name = name;
        this.dateCreation = dateCreation;
        this.dateReceived = dateReceived;
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateReceived=" + dateReceived +
                ", total=" + total +
                '}';
    }
}
