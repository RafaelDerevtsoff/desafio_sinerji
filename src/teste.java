import Entitites.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class teste {
    private static List<Employee> employees = new ArrayList<>();
    private static List<Sale> ana = new ArrayList<>();
    private static List<Sale> joao = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        ana.add(new Sale(5200.0, LocalDate.of(2021, 12, 1)));
        ana.add(new Sale(4000.0, LocalDate.of(2022, 1, 1)));
        ana.add(new Sale(4200.0, LocalDate.of(2022, 2, 1)));
        ana.add(new Sale(5850.0, LocalDate.of(2022, 3, 1)));
        ana.add(new Sale(7000.0, LocalDate.of(2022, 4, 1)));

        joao.add(new Sale(3400.0, LocalDate.of(2021, 12, 1)));
        joao.add(new Sale(7700.0, LocalDate.of(2022, 1, 1)));
        joao.add(new Sale(5000.0, LocalDate.of(2022, 2, 1)));
        joao.add(new Sale(5900.0, LocalDate.of(2022, 3, 1)));
        joao.add(new Sale(6500.0, LocalDate.of(2022, 4, 1)));

        employees.add(new Secretary("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
        employees.add(new Secretary("Maria Souza", LocalDate.of(2015, Month.of(12), 1)));
        employees.add(new Seller("Ana Silva", LocalDate.of(2021, Month.of(12), 1), ana));
        employees.add(new Seller("João Mendes", LocalDate.of(2021, Month.of(12), 1), joao));
        employees.add(new Manager("Juliana Alves", LocalDate.of(2017, Month.of(7), 1)));
        employees.add(new Manager("Bento Albino", LocalDate.of(2014, Month.of(3), 1)));
    }

    @Test
    public void calculateTotalToAllEmployees() {
        int month = 1;
        int year = 2022;
        System.out.println(employees
                .stream()
                .map(e -> e.calculateTotal(year, month))
                .reduce(0.0, Double::sum));
    }

    @Test
    public void calculateSalaryToAllEmployees() {
        int month = 1;
        int year = 2022;
        LocalDate s = LocalDate.of(year, month, 1);
        System.out.println(employees
                .stream()
                .map(e -> e.calculateSalary((int) ChronoUnit.YEARS.between(e.getHiringDate(), s)))
                .reduce(0.0, Double::sum));
    }

    @Test
    public void maxBenefits() {
        int month = 1;
        int year = 2022;
        List<Employee> hasBenefits = employees
                .stream()
                .filter(Employee::hasBenefits)
                .toList();
        Employee highetsBenefit = hasBenefits.get(0);
        for (Employee b : hasBenefits) {
            Benefits currentBenefit = ((Benefits) b);
            if (currentBenefit.calculateBenefits(year, month) > ((Benefits) highetsBenefit).calculateBenefits(year, month)) {
                highetsBenefit = b;
            }
        }
        System.out.println(highetsBenefit.getName());
    }

    @Test
    public void calculateTotalBenefits() {
        int month = 1;
        int year = 2022;
        List<Employee> hasBenefits = employees
                .stream()
                .filter(Employee::hasBenefits)
                .toList();
        System.out.println(hasBenefits.stream().map(b -> {
            return ((Benefits) b).calculateBenefits(year, month);
        }).reduce(0.0, Double::sum));
    }

    @Test
    public void max() {
        int month = 1;
        int year = 2022;
        Employee employeeWithHighestSalary = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateTotal(year, month) > employeeWithHighestSalary.calculateTotal(year, month)) {
                employeeWithHighestSalary = e;
            }
        }
        System.out.println(employeeWithHighestSalary.getName());
    }

    @Test
    public void bestSeller() {
        int month = 1;
        int year = 2022;
        List<Employee> sellers = employees
                .stream()
                .filter(e -> e.getClass() == Seller.class)
                .toList();
        double max = 0.0;
        Seller se = null;
        for (Employee seller : sellers) {
            Seller s = ((Seller) seller);
            Double currentTotal = s.getSales().stream().map(Sale::getValor).reduce(0.0, Double::sum);
            if (currentTotal > max) {
                max = currentTotal;
                se = s;
            }
        }
        System.out.println(se.getName());
    }

}
