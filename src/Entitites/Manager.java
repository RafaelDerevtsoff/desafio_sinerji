package Entitites;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Manager extends Employee implements Salary {
    private final Long baseSalary = 20000L;

    public Manager(String name, LocalDate hiringDate) {
        super(name, hiringDate);
    }

    public Long getBaseSalary() {
        return baseSalary;
    }

    public Long getAdditionalPerYear() {
        return 3000L;
    }

    @Override
    public Long calculateSalary(Integer time) {
        return baseSalary + (getAdditionalPerYear() * time);
    }

    @Override
    public Double calculateTotal(Integer year, Integer month) {
        int between = (int) ChronoUnit.YEARS.between(this.getHiringDate(), LocalDate.of(year, month, 1));
        return (double) calculateSalary(between);
    }
}
