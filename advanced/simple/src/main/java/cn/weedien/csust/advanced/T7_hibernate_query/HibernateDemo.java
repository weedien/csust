package cn.weedien.csust.advanced.T7_hibernate_query;

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
