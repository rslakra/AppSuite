package com.rslakra.core.http;

import java.math.BigDecimal;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 3/29/21 12:00 PM
 */
public class MockObject {

    private Long id;
    private String name;
    private Boolean active;
    private MockStatus status;
    private BigDecimal amount;

    public MockObject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public MockStatus getStatus() {
        return status;
    }

    public void setStatus(MockStatus status) {
        this.status = status;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return new StringBuilder("MockObject <")
            .append("id=").append(getId())
            .append(", name=").append(getName())
            .append(", active=").append(getActive())
            .append(", status=").append(getStatus())
            .append(", amount=").append(getAmount())
            .append(">").toString();
    }
}
