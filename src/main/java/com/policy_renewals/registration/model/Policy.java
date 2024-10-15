package com.policy_renewals.registration.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Policies_Table")
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long policyId;

    private Double price;
    private String policyName;
    private Integer period;
    private String description;

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPolicyName() {
        return policyName;
    }

    public void setPolicyName(String policyName) {
        this.policyName = policyName;
    }

    public Policy(Long policyId, String description, Integer period, String policyName, Double price) {
        this.policyId = policyId;
        this.description = description;
        this.period = period;
        this.policyName = policyName;
        this.price = price;
    }

    public Policy() {
        super();
    }

    @Override
    public String toString() {
        return "Policy{" +
                "policyId=" + policyId +
                ", price=" + price +
                ", policyName='" + policyName + '\'' +
                ", period=" + period +
                ", description='" + description + '\'' +
                '}';
    }
}

