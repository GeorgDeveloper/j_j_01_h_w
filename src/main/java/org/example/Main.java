package org.example;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.example.RandomNumber.*;

public class Main {
    public static void main(String[] args) {


        //Создать список из 1_000 рандомных чисел от 1 до 1_000_000
        List<Integer> listRandomNumbers = Stream.generate(() -> getRandomNumber(1_000_000))
                .limit(1_000_000)
                .collect(Collectors.toList());

        //listRandomNumbers.forEach(System.out::println);

        // Найти максимальное
        long maxNumber = listRandomNumbers.stream().max(Comparator.comparingInt(x -> x)).get();
        System.out.println("maxNumber " + maxNumber);


        //Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать

        int mixNumber = listRandomNumbers.stream().filter(x -> x > 500000)
                .map(x-> x*5 - 150)
                .mapToInt(num -> num.intValue())
                .sum();
        System.out.println("mixNumber " + mixNumber);

        //Найти количество чисел, квадрат которых меньше, чем 100_000

        int countNumber = (int) listRandomNumbers.stream().filter(x -> Math.pow(x, 2) < 100000).count();
        System.out.println("countNumber " + countNumber);


        //Создать список из 10-20 сотрудников
        List<Employee> employeeList = Arrays.asList(
                new Employee("Максим", 32, 100000, "IT"),
                new Employee("Антон", 23, 35000, "Technical"),
                new Employee("Мария", 27, 100000, "Quality"),
                new Employee("Алексей", 40, 80000, "IT"),
                new Employee("Ирина", 36, 85000, "IT"),
                new Employee("Артем", 32, 54000, "Quality"),
                new Employee("Виктория", 32, 28000, "Quality"),
                new Employee("Дмитрий", 32, 31000, "Technical"),
                new Employee("Алекс", 32, 32000, "Technical"),
                new Employee("Иван", 32, 40000, "Technical"),
                new Employee("Яна", 32, 41000, "Technical")
                );

        //Вывести список всех различных отделов (department) по списку сотрудников
        List<String> department = employeeList.stream().map(x -> x.getDepartment()).distinct().toList();
        department.forEach(System.out::println);


        //Всем сотрудникам, чья зарплата меньше 100_000, повысить зарплату на 20%
        List<Employee> newEmployeeList = employeeList.stream().filter(employeeSalari -> employeeSalari.getSalary() < 100000)
                .map(newEmployeeSalari -> {
                    double cnt = newEmployeeSalari.getSalary() * 1.2;
                     newEmployeeSalari.setSalary(cnt);
                    return newEmployeeSalari;
                }).toList();
        System.out.println("Сотрудники, чья зарплата  повысилась на 20%");
        newEmployeeList.forEach(System.out::println);

        // Создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
        Map<String, List<Employee>> employeesByDepartment = newEmployeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
        System.out.println("Сотрудники по отделам: " + employeesByDepartment);

        // Создать Map<String, Double> с отделами и средней зарплатой внутри отдела
        Map<String, Double> averageSalaryByDepartment = newEmployeeList.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)));
        System.out.println("Средняя зарплата по отделам: " + averageSalaryByDepartment);
    }
}