package com.policy_renewals.registration.DTO;

import java.util.Date;

public class PolicyDetailsDTO {
    private Double broughtPrice;
    private Date broughtDate;
    private Integer broughtPeriod;
    private String holderName;
    private String holderAddress;
    private String email;
    private String policyName;
    private Double policyPrice;
    private Integer policyPeriod;
    private String description;
    private String policyStatus;

    // Constructors, Getters, Setters


    public PolicyDetailsDTO(Double broughtPrice, Date broughtDate, Integer broughtPeriod, String holderName, String holderAddress, String email, String policyName, Double policyPrice, Integer policyPeriod, String description, String policyStatus) {
        this.broughtPrice = broughtPrice;
        this.broughtDate = broughtDate;
        this.broughtPeriod = broughtPeriod;
        this.holderName = holderName;
        this.holderAddress = holderAddress;
        this.email = email;
        this.policyName = policyName;
        this.policyPrice = policyPrice;
        this.policyPeriod = policyPeriod;
        this.description = description;
        this.policyStatus = policyStatus;
    }

    public PolicyDetailsDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPolicyPeriod() {
        return policyPeriod;
    }

    public void setPolicyPeriod(Integer policyPeriod) {
        this.policyPeriod = policyPeriod;
    }

    public Double getPolicyPrice() {
        return policyPrice;
    }

    public void setPolicyPrice(Double policyPrice) {
        this.policyPrice = policyPrice;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHolderAddress() {
        return holderAddress;
    }

    public void setHolderAddress(String holderAddress) {
        this.holderAddress = holderAddress;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Integer getBroughtPeriod() {
        return broughtPeriod;
    }

    public void setBroughtPeriod(Integer broughtPeriod) {
        this.broughtPeriod = broughtPeriod;
    }

    public Date getBroughtDate() {
        return broughtDate;
    }

    public void setBroughtDate(Date broughtDate) {
        this.broughtDate = broughtDate;
    }

    public Double getBroughtPrice() {
        return broughtPrice;
    }

    public void setBroughtPrice(Double broughtPrice) {
        this.broughtPrice = broughtPrice;
    }

    public String getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(String policyStatus) {
        this.policyStatus = policyStatus;
    }
}
