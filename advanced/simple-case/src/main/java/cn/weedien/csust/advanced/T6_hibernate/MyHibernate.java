package cn.weedien.csust.advanced.T6_hibernate;

import java.io.Serializable;

public class MyHibernate {

    public static void main(String[] args) {
        MyHibernate h = new MyHibernate();
        StudentDO student = h.load(StudentDO.class, 1);
        System.out.println(student);
    }

    public <T> T load(Class<T> clazz, Serializable serializable) {
        ConfigParser config = new ConfigParser("Student.properties");

        String query = config.generateQuery("id");

        return JDBCTemplate.queryForObject(query, clazz, serializable);
    }
}