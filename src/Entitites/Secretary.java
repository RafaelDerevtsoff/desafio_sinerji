package Entitites;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Secretary extends Employee implements Salary, Benefits {
    private final Long baseSalary = 7000L;

    public Secretary(String name, LocalDate hiringDate) {
        super(name, hiringDate);
    }

    @Override
    public boolean hasBenefits() {
        return true;
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public Long getAdditionalPerYear() {
        return 1000L;
    }

    @Override
    public Double calculateBenefits(Integer year,Integer month) {
        return baseSalary * 0.02;
    }

    @Override
    public Double calculateSalary(Integer time) {
        return (double) (baseSalary + (getAdditionalPerYear() * time));
    }

    @Override
    public Double calculateTotal(Integer year,Integer month) {
        int between = (int) ChronoUnit.YEARS.between(this.getHiringDate(), LocalDate.of(year, month, 1));
        return this.calculateSalary(between) + this.calculateBenefits(year, month);
    }
}
