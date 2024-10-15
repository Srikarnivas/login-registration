package com.policy_renewals.registration.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Policies_Brought_Table")
public class PoliciesBrought {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @Column(name = "policy_Id")
    private Long policyId;

    private Double price;
    private Date broughtDate;
    private Integer period; // In months
    private String holderName;
    private String holderAddress;
    private String email;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getBroughtDate() {
        return broughtDate;
    }

    public void setBroughtDate(Date broughtDate) {
        this.broughtDate = broughtDate;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolderAddress() {
        return holderAddress;
    }

    public void setHolderAddress(String holderAddress) {
        this.holderAddress = holderAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PoliciesBrought(Long transactionId, String email, String holderAddress, String holderName, Date broughtDate, Integer period, Double price, Long policyId) {
        this.transactionId = transactionId;
        this.email = email;
        this.holderAddress = holderAddress;
        this.holderName = holderName;
        this.broughtDate = broughtDate;
        this.period = period;
        this.price = price;
        this.policyId = policyId;
    }

    public PoliciesBrought() {
        super();
    }

    @Override
    public String toString() {
        return "PoliciesBrought{" +
                "transactionId=" + transactionId +
                ", policyId=" + policyId +
                ", price=" + price +
                ", broughtDate=" + broughtDate +
                ", period=" + period +
                ", holderName='" + holderName + '\'' +
                ", holderAddress='" + holderAddress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
