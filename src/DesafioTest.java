import Entitites.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DesafioTest {
    private static final List<Employee> employees = new ArrayList<>();
    private static final List<Sale> ana = new ArrayList<>();
    private static final List<Sale> joao = new ArrayList<>();

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


    private static Double CalculateTotal(List<Employee> employees, int year, int month) {
        return employees
                .stream()
                .map(e -> e.calculateTotal(year, month))
                .reduce(0.0, Double::sum);
    }

    public Double calculateTotalSalary(List<Employee> employees, int year, int month) {
        LocalDate date = LocalDate.of(year, month, 1);
        return employees
                .stream()
                .map(e -> e.calculateSalary((int) ChronoUnit.YEARS.between(e.getHiringDate(), date)))
                .reduce(0.0, Double::sum);
    }

    public Employee highestPaidEmployee(List<Employee> employees, int year, int month) {
        Employee employeeWithHighestSalary = employees.get(0);
        for (Employee e : employees) {
            if (e.calculateTotal(year, month) > employeeWithHighestSalary.calculateTotal(year, month)) {
                employeeWithHighestSalary = e;
            }
        }
        return employeeWithHighestSalary;
    }

    public Double calculateTotalBenefits(List<Benefits> benefits, int year, int month) {
        return benefits.stream().map(b -> b.calculateBenefits(year, month)).reduce(0.0, Double::sum);
    }

    public String highestBenefit(List<Benefits> benefits, int year, int month) {
        Benefits highetsBenefit = benefits.get(0);
        for (Benefits currentBenefit : benefits) {
            if (currentBenefit.calculateBenefits(year, month) > highetsBenefit.calculateBenefits(year, month)) {
                highetsBenefit = currentBenefit;
            }
        }
        return ((Employee) highetsBenefit).getName();
    }

    public Employee bestSeller(List<Seller> sellers, int year, int month) {
        double max = 0.0;
        Seller bestSellet = null;
        for (Seller seller : sellers) {
            Double currentTotal = seller
                    .getSales()
                    .stream()
                    .filter(s -> Objects.equals(s.getDate(), LocalDate.of(year, month, 1)))
                    .map(Sale::getValor)
                    .reduce(0.0, Double::sum);
            if (currentTotal > max) {
                max = currentTotal;
                bestSellet = seller;
            }
        }
        return bestSellet;
    }


    @Test
    public void calculateTotalToAllEmployees() {
        int month = 1;
        int year = 2022;
        System.out.println(CalculateTotal(employees, year, month));
    }
    @Test
    public void calculateSalaryToAllEmployees() {
        int month = 1;
        int year = 2022;
        LocalDate s = LocalDate.of(year, month, 1);
        System.out.println(calculateTotalSalary(employees,2022,month));
    }


    @Test
    public void maxBenefits() {
        int month = 1;
        int year = 2022;
        List<Benefits> benefits = new ArrayList<>();
        benefits.add(new Secretary("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
        benefits.add(new Secretary("Maria Souza", LocalDate.of(2015, Month.of(12), 1)));
        benefits.add(new Seller("Ana Silva", LocalDate.of(2021, Month.of(12), 1), ana));
        benefits.add(new Seller("João Mendes", LocalDate.of(2021, Month.of(12), 1), joao));
        System.out.println(calculateTotalBenefits(benefits,year,month));
    }

    @Test
    public void calculateTotalBenefits() {
        int month = 1;
        int year = 2022;
        List<Benefits> benefits = new ArrayList<>();
        benefits.add(new Secretary("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
        benefits.add(new Secretary("Maria Souza", LocalDate.of(2015, Month.of(12), 1)));
        benefits.add(new Seller("Ana Silva", LocalDate.of(2021, Month.of(12), 1), ana));
        benefits.add(new Seller("João Mendes", LocalDate.of(2021, Month.of(12), 1), joao));
        System.out.println(calculateTotalBenefits(benefits,year,month));
    }

    @Test
    public void max() {
        int month = 1;
        int year = 2022;
        System.out.println(highestPaidEmployee(employees,year,month).getName());
    }

    @Test
    public void bestSeller() {
        int month = 1;
        int year = 2022;
        List<Seller> sellers = new ArrayList<Seller>();
        sellers.add(new Seller("Ana Silva", LocalDate.of(2021, Month.of(12), 1), ana));
        sellers.add(new Seller("João Mendes", LocalDate.of(2021, Month.of(12), 1), joao));
        System.out.println(bestSeller(sellers,year,month).getName());
    }
    @Test
    public void highestBenefit() {
        int month = 1;
        int year = 2022;
        List<Benefits> benefits = new ArrayList<>();
        benefits.add(new Secretary("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
        benefits.add(new Secretary("Maria Souza", LocalDate.of(2015, Month.of(12), 1)));
        benefits.add(new Seller("Ana Silva", LocalDate.of(2021, Month.of(12), 1), ana));
        benefits.add(new Seller("João Mendes", LocalDate.of(2021, Month.of(12), 1), joao));
        System.out.println(highestBenefit(benefits,year,month));
    }

}
