## 任务描述

了解数据持久化原理及相关技术，完成如下任务：

（1）建立Java项目，添加Hibernate框架支持；

（2）创建学生表格Student(sid,sname,age);

（3）使用Hibernate逆向工程建立POJO类Student和ORM映射文件；

（4）利用Hibernate支持编写StudentDAO类，实现学生的增删改查

（5）将框架的主要配置、代码及程序运行截图形成一个word文档提交

## 运行结果

![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/20240329140449.png)

![image.png](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/20240329140508.png)

## 核心代码

- 映射文件

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE hibernate-mapping PUBLIC  
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"  
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">  
  
<hibernate-mapping>  
    <class name="com.example.StudentDO" table="student">  
        <id name="sid" column="sid">  
            <generator class="native"/>  
        </id>        <property name="sname" column="sname" length="64" not-null="true"/>  
        <property name="age" column="age" not-null="true"/>  
    </class></hibernate-mapping>
```

- Hibernate配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE hibernate-configuration PUBLIC  
        "-//Hibernate/Hibernate Configuration DTD 3.0//EN"  
        "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd">  
  
<hibernate-configuration>  
    <session-factory>        <!-- Database connection settings -->  
        <property name="connection.driver_class">com.mysql.cj.jdbc.Driver</property>  
        <property name="connection.url">jdbc:mysql://localhost:3306/test</property>  
        <property name="connection.username">username</property>  
        <property name="connection.password">password</property>  
  
        <!-- JDBC connection pool (use the built-in) -->  
        <property name="connection.pool_size">10</property>  
  
        <!-- SQL dialect -->  
        <property name="dialect">org.hibernate.dialect.MySQLDialect</property>  
  
        <!-- Disable the second-level cache  -->  
        <property name="cache.provider_class">org.hibernate.cache.internal.DisabledCaching</property>  
  
        <!-- Echo all executed SQL to stdout -->  
        <property name="show_sql">true</property>  
  
        <!-- Drop and re-create the database schema on startup -->  
        <property name="hbm2ddl.auto">update</property>  
  
        <!-- Names the annotated entity class -->        <mapping class="com.example.StudentDO"/>  
    </session-factory></hibernate-configuration>
```

- Student数据库对象

```java
import jakarta.persistence.*;  
import lombok.Data;  
import lombok.NoArgsConstructor;  
  
@Data  
@NoArgsConstructor  
@Entity  
@Table(name = "student")  
public class StudentDO {  
    @Id  
    @GeneratedValue(strategy = GenerationType.AUTO)  
    private Integer sid;  
  
    @Column(name = "sname", length = 64, nullable = false)  
    private String sname;  
  
    @Column(name = "age", nullable = false)  
    private Integer age;  
  
    public StudentDO(String sname, Integer age) {  
        this.sname = sname;  
        this.age = age;  
    }  
}
```

- Hibernate工具类

```java
/**  
 * Hibernate 工具类  
 */  
public class HibernateUtil {  
  
    @Getter  
    private static final SessionFactory sessionFactory;  
  
    static {  
        try {  
            // 从hibernate.cfg.xml中创建SessionFactory  
            sessionFactory = new Configuration().configure().buildSessionFactory();  
        } catch (Throwable ex) {  
            System.err.println("Initial SessionFactory creation failed." + ex);  
            throw new ExceptionInInitializerError(ex);  
        }  
    }  
}
```

- 学生数据访问对象

```java
import jakarta.persistence.criteria.CriteriaBuilder;  
import jakarta.persistence.criteria.CriteriaQuery;  
import jakarta.persistence.criteria.Root;  
import org.hibernate.Session;  
  
import java.util.List;  
  
/**  
 * 学生数据访问对象  
 */  
public class StudentDAO {  
  
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
}
```