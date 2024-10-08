package com.dev.pos.Entity;

import java.util.Date;

public class OrderDetail implements SuperEntity {

    private int code;
    private Date issueDate;
    private double totalCost;
    private String customerEmail;
    private double discount;
    private String userEmail;

    public OrderDetail() {
    }

    public OrderDetail(int code, Date issueDate, double totalCost, String customerEmail, double discount, String userEmail) {
        this.code = code;
        this.issueDate = issueDate;
        this.totalCost = totalCost;
        this.customerEmail = customerEmail;
        this.discount = discount;
        this.userEmail = userEmail;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
