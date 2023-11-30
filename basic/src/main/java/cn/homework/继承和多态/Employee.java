package cn.homework.继承和多态;

import java.util.Date;

class Employee extends Person {
    private String office;
    private double salary;
    private Date hireDate;

    public Employee(String name, String address, String phoneNumber, String email, String office, double salary,
            Date hireDate) {
        super(name, address, phoneNumber, email);
        this.office = office;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public String getOffice() {
        return office;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return "Employee: " + getName();
    }
}
