package cn.weedien.ee.expe03.test02;

import lombok.Data;

@Data
class Person {
    String name;
    int age;
    String sex;

    public Person() {
    }

    public Person(String name, int age, String sex) {
        super();
        this.name = name;
        this.age = age;
        this.sex = sex;
    }

    public String toString() {
        return "Student [name=" + name + ", age=" + age + ", sex=" + sex + "]";
    }

}