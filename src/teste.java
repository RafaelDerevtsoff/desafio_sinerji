import Entitites.Employee;
import Entitites.Manager;
import Entitites.Secretary;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class teste {
    private static List<Employee> employees = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        employees.add(new Secretary("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
        employees.add(new Manager("Jorge Carvalho", LocalDate.of(2018, Month.of(1), 1)));
    }

    @Test
    public void calculateTotalToAllEmployees() {
        int month = 1;
        int year = 2023;
        System.out.println(employees
                .stream()
                .map(e -> e.calculateTotal(year, month))
                .reduce(0.0, Double::sum));
    }
}
