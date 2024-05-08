package cn.weedien.csust.advanced.T8_mybatis;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Slf4j
public class StudentDemo {

    public static void main(String[] args) {
        String resource = "mybatis-config.xml";
        InputStream is = StudentDemo.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = null;
        try {
            is = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
            // 增加学生记录
            StudentDO student = new StudentDO(1, "张三", 90);
            sqlSession.insert("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.insertStudent", student);
            log.info("新增学生：{}", student);

            // 查询所有学生
            List<StudentDO> students = sqlSession.selectList("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.findAllStudents");
            log.info("所有学生：");
            students.forEach(s -> log.info("{}", s));

            // 修改学生记录
            student.setScore(95);
            sqlSession.update("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.updateStudent", student);

            // 查询修改后的学生信息
            StudentDO student1 = sqlSession.selectOne("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.findStudentBySid", 1);
            log.info("修改后的学生信息：{}", student1);

            // 删除学生记录
            sqlSession.delete("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.deleteStudent", 1);

            // 查询所有学生
            students = sqlSession.selectList("cn.weedien.csust.advanced.T8_mybatis.StudentMapper.findAllStudents");
            log.info("所有学生：");
            students.forEach(s -> log.info("{}", s));
        }
    }
}