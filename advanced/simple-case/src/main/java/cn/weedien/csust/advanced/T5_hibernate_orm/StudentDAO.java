package cn.weedien.csust.advanced.T5_hibernate_orm;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

/**
 * 学生数据访问对象
 */
public class StudentDAO {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // 创建学生
        StudentDO student = new StudentDO("张妙妙", 20);
        studentDAO.createStudent(student);

        // 查询所有学生
        List<StudentDO> students = studentDAO.listStudents();
        students.forEach(System.out::println);

        // 更新学生信息
        student.setAge(21);
        studentDAO.updateStudent(student);

        // 查询所有学生
        students = studentDAO.listStudents();
        students.forEach(System.out::println);

        // 删除学生
        studentDAO.deleteStudent(student);

        // 查询所有学生
        students = studentDAO.listStudents();
        students.forEach(System.out::println);
    }

    /**
     * 创建一个学生
     *
     * @param student 学生信息
     */
    private void createStudent(StudentDO student) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.persist(student);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     */
    private void updateStudent(StudentDO student) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.merge(student);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * 删除学生
     *
     * @param student 学生信息
     */
    private void deleteStudent(StudentDO student) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.remove(student);
        session.getTransaction().commit();

        session.close();
    }

    /**
     * 查询所有学生
     *
     * @return 学生列表
     */
    private List<StudentDO> listStudents() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<StudentDO> cr = cb.createQuery(StudentDO.class);
        Root<StudentDO> root = cr.from(StudentDO.class);
        cr.select(root);

        session.beginTransaction();
        List<StudentDO> students = session.createQuery(cr).list();
        session.getTransaction().commit();

        session.close();
        return students;
    }
}
