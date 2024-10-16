package com.policy_renewals.registration.DTO;


public class PolicyDTO {

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

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PolicyDTO(Long policyId, Double price, String policyName, Integer period, String description) {
        this.policyId = policyId;
        this.price = price;
        this.policyName = policyName;
        this.period = period;
        this.description = description;
    }

    public PolicyDTO() {
        super();
    }

    @Override
    public String toString() {
        return "PolicyDTO{" +
                "policyId=" + policyId +
                ", price=" + price +
                ", policyName='" + policyName + '\'' +
                ", period=" + period +
                ", description='" + description + '\'' +
                '}';
    }
}
