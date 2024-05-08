package cn.weedien.csust.basic.homework.继承和多态;

import java.util.Date;

public class Test {
    public static void main(String[] args) {
        Person person = new Person("John Smith", "123 Main St", "555-1234", "john.smith@example.com");
        System.out.println(person.toString());

        Student student = new Student("Jane Doe", "456 Oak St", "555-5678", "jane.doe@example.com", Student.JUNIOR);
        System.out.println(student.toString());

        Date hireDate = new Date();
        Employee employee = new Employee("Bob Johnson", "789 Maple St", "555-9012", "bob.johnson@example.com", "123",
                50000, hireDate);
        System.out.println(employee.toString());

        Faculty faculty = new Faculty("Alice Brown", "321 Pine St", "555-3456", "alice.brown@example.com", "456", 80000,
                hireDate, "9-11am", "Professor");
        System.out.println(faculty.toString());

        Staff staff = new Staff("Tom Wilson", "654 Elm St", "555-7890", "tom.wilson@example.com", "789", 40000,
                hireDate, "Manager");
        System.out.println(staff.toString());
    }
}