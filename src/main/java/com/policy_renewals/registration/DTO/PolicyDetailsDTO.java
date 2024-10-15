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

    // Constructors, Getters, Setters

    public PolicyDetailsDTO(Double broughtPrice, String description, Integer policyPeriod, Double policyPrice, String email, String policyName, String holderAddress, String holderName, Integer broughtPeriod, Date broughtDate) {
        this.broughtPrice = broughtPrice;
        this.description = description;
        this.policyPeriod = policyPeriod;
        this.policyPrice = policyPrice;
        this.email = email;
        this.policyName = policyName;
        this.holderAddress = holderAddress;
        this.holderName = holderName;
        this.broughtPeriod = broughtPeriod;
        this.broughtDate = broughtDate;
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

}
