package Entitites;

public interface Salary {
    Double calculateSalary(Integer time);
    Double calculateTotal(Integer year,Integer month);
    boolean hasBenefits();
}
