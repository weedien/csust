package cn.weedien.csust.basic.homework.继承和多态;

import java.util.Date;

class Faculty extends Employee {
    private final String officeHours;
    private final String rank;

    public Faculty(String name, String address, String phoneNumber, String email, String office, double salary,
                   Date hireDate, String officeHours, String rank) {
        super(name, address, phoneNumber, email, office, salary, hireDate);
        this.officeHours = officeHours;
        this.rank = rank;
    }

    public String getOfficeHours() {
        return officeHours;
    }

    public String getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Faculty: " + getName();
    }
}
