package Entitites;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Seller extends Employee implements Salary, Benefits {
    private final Long baseSalary =  12000L;

    public Seller(String name, LocalDate hiringDate) {
        super(name, hiringDate);
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public Long getAdditionalPerYear() {
        return 1800L;
    }

    @Override
    public Double calculateBenefits() {
        return baseSalary * 0.03;
    }

    @Override
    public Long calculateSalary(Integer time) {
        return baseSalary + (getAdditionalPerYear() * time);
    }

    @Override
    public Double calculateTotal(Integer year, Integer month) {
        int between = (int) ChronoUnit.YEARS.between(this.getHiringDate(), LocalDate.of(year, month, 1));
        return this.calculateSalary(between) + this.calculateBenefits();
    }
}
