package org.interactive.debts.calculator;

/**
 * Payment class
 *
 * @author bogdan.solga
 *
 * Date: 20.08.2013, time: 09:32
 */
public class Payment {

    private Integer id;

    private Double amount;

    private String description;

    public Payment(Integer id, Double amount, String description) {
        this.id = id;
        this.amount = amount;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
