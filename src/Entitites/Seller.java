package Entitites;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Seller extends Employee implements Sales, Benefits {
    private final Long baseSalary = 12000L;


    private List<Sale> sales;
    public List<Sale> getSales() {
        return sales;
    }

    public Seller(String name, LocalDate hiringDate, List<Sale> sales) {
        super(name, hiringDate);
        this.sales = sales;
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public Long getAdditionalPerYear() {
        return 1800L;
    }
    @Override
    public Double calculateSalary(Integer time) {
        return (double) (baseSalary + (getAdditionalPerYear() * time));
    }

    @Override
    public boolean hasBenefits() {
        return true;
    }

    @Override
    public Double calculateTotal(Integer year, Integer month) {
        int between = (int) ChronoUnit.YEARS.between(this.getHiringDate(), LocalDate.of(year, month, 1));
        return this.calculateSalary(between) + this.calculateCommission(month, year);
    }

    @Override
    public Double calculateCommission(Integer month, Integer year) {
        return sales
                .stream()
                .filter(sale -> sale.getDate().equals(LocalDate.of(year, month, 1)))
                .findFirst()
                .get().getValor() * 0.03;
    }
    @Override
    public Double calculateBenefits(Integer year,Integer month) {
        return sales
                .stream()
                .filter(sale -> sale.getDate().equals(LocalDate.of(year, month, 1)))
                .findFirst()
                .get().getValor() * 0.03;
    }
}
