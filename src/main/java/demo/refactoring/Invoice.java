package demo.refactoring;

import java.util.List;
import java.util.Objects;

public class Invoice {

    private String customer;

    private List<Performance> performances;

    public Invoice(String customer, List<Performance> performances) {
        this.customer = customer;
        this.performances = performances;
    }

    public String getCustomer() {
        return customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Invoice)) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(customer, invoice.customer) &&
                Objects.equals(performances, invoice.performances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, performances);
    }

    public List<Performance> getPerformances() {
        return performances;
    }
}
