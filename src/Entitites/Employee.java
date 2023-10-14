package Entitites;

import java.time.LocalDate;

public class Employee implements Salary {
    private String name;
    private LocalDate hiringDate;

    @Override
    public boolean hasBenefits() {
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(LocalDate hiringDate) {
        this.hiringDate = hiringDate;
    }

    public Employee(String name, LocalDate hiringDate) {
        this.name = name;
        this.hiringDate = hiringDate;
    }

    @Override
    public Double calculateSalary(Integer time) {
        return 0.0;
    }

    @Override
    public Double calculateTotal(Integer year, Integer month) {
        return null;
    }
}
