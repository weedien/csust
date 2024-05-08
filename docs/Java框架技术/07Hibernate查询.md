## 任务要求

掌握Hibernate HQL查询语句，完成如下任务：

（1）设计权限管理用户组Group和角色组Role，实现多对多关联数据库设计和域模型设计；

（2）编写Group.hbm.xml和Role.hbm.xml，配置多对多映射；

（3）给定用户组Group，查询对应的角色Role；

（4）观察SQL输出，分析查询的主要过程；

（5）将任务的主要思路、代码、配置文件和程序运行截图形成一个word文件提交。

## 核心代码

- UserGroup

```java
@Entity
@Table(name = "user_group")
public class UserGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_role",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRole> userRoles = new HashSet<>();

    public UserGroup() {
    }

    public UserGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserRole> getRoles() {
        return userRoles;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```

- UserRole

```java
@Entity
@Table(name = "user_role")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "userRoles")
    private Set<UserGroup> groups = new HashSet<>();

    public UserRole() {
    }

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserGroup> getGroups() {
        return groups;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroups(Set<UserGroup> groups) {
        this.groups = groups;
    }
}
```

- Group.hbm.xml

```xml
<hibernate-mapping>
    <class name="cn.weedien.csust.advanced.T7_hibernate_query.UserGroup" table="user_group">
        <id name="id" column="id">
            <generator class="identity"/>
        </id>
        <property name="name" column="name" not-null="true"/>
        <set name="userRoles" table="group_role" inverse="true">
            <key column="group_id"/>
            <many-to-many column="role_id" class="cn.weedien.csust.advanced.T7_hibernate_query.UserRole"/>
        </set>
    </class>
</hibernate-mapping>

```

- Role.hbm.xml

```java
<hibernate-mapping>
    <class name="cn.weedien.csust.advanced.T7_hibernate_query.UserRole" table="user_role">
        <id name="id" column="id">
            <generator class="identity"/>
        </id>
        <property name="name" column="name" not-null="true"/>
        <set name="groups" table="group_role">
            <key column="role_id"/>
            <many-to-many column="group_id" class="cn.weedien.csust.advanced.T7_hibernate_query.UserGroup"/>
        </set>
    </class>
</hibernate-mapping>
```

- HibernateDemo

```java
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class HibernateDemo {

    private static SessionFactory sessionFactory;

    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();

        insertMock();

        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();

        // 获取用户组
        UserGroup group = session.get(UserGroup.class, 1);

        // 使用 HQL 查询角色
        String hql = "SELECT r FROM UserRole r JOIN r.groups g WHERE g = :group";
        Query<UserRole> query = session.createQuery(hql, UserRole.class);
        query.setParameter("group", group);
        List<UserRole> userRoles = query.list();

        // 输出角色
        for (UserRole userRole : userRoles) {
            System.out.println(userRole.getName());
        }

        tx.commit();
        session.close();
    }

    public static void insertMock() {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        // 插入用户组
        UserGroup group1 = new UserGroup();
        group1.setName("Administrators");
        session.persist(group1);

        UserGroup group2 = new UserGroup();
        group2.setName("Users");
        session.persist(group2);

        // 插入角色
        UserRole userRole1 = new UserRole();
        userRole1.setName("Admin");
        session.persist(userRole1);

        UserRole userRole2 = new UserRole();
        userRole2.setName("Editor");
        session.persist(userRole2);

        UserRole userRole3 = new UserRole();
        userRole3.setName("Viewer");
        session.persist(userRole3);

        // 建立用户组和角色之间的关联关系
        group1.getRoles().add(userRole1);
        group1.getRoles().add(userRole2);

        group2.getRoles().add(userRole2);
        group2.getRoles().add(userRole3);

        tx.commit();
        session.close();
    }
}
```

## 运行截图

- user_role

![image-20240412213415075](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/image-20240412213415075.png)

- user_group

![image-20240412213426343](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/image-20240412213426343.png)

- group_role

![image-20240412213439136](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/image-20240412213439136.png)

- 运行过程中生成的sql

![image-20240412213630533](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/image-20240412213630533.png)