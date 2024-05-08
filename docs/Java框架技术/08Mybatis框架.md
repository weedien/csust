## 任务要求

掌握MyBatis框架的基本原理和主要操作，完成如下任务：

（1）安装MyBatis框架

（2）理解MyBatis框架配置

（3）建立表格：Student(sid,sname,score)，编写域模型Student类，编写StudentMapper.xml映射文件

（4）获取SqlSession实例，实现Student的增、删、改、查操作

（5）将任务主要思路、代码、配置文件内容、程序运行截图形成一个word文件提交。

## 核心代码

- mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    <settings>
        <setting name="logImpl" value="SLF4J"/>
    </settings>

    <!-- 配置环境 -->
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/test"/>
                <property name="username" value="root"/>
                <property name="password" value="password"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="StudentMapper.xml"/>
    </mappers>

</configuration>
```

- StudentMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.example.StudentMapper">
    <!-- 定义映射 -->
    <resultMap id="studentResultMap" type="com.example.StudentDO">
        <id property="sid" column="sid"/>
        <result property="sname" column="sname"/>
        <result property="score" column="score"/>
    </resultMap>
    <!-- 查询所有学生 -->
    <select id="findAllStudents" resultMap="studentResultMap">
        SELECT sid, sname, score
        FROM student
    </select>

    <!-- 根据学号查询学生 -->
    <select id="findStudentBySid" parameterType="int" resultMap="studentResultMap">
        SELECT sid, sname, score
        FROM student WHERE sid = #{sid}
    </select>

    <!-- 插入学生记录 -->
    <insert id="insertStudent" parameterType="com.example.StudentDO">
        INSERT INTO student (sid, sname, score)
        VALUES (#{sid}, #{sname}, #{score})
    </insert>

    <!-- 更新学生记录 -->
    <update id="updateStudent" parameterType="com.example.StudentDO">
        UPDATE student
        SET sname = #{sname}, score = #{score} WHERE sid = #{sid}
    </update>

    <!-- 删除学生记录 -->
    <delete id="deleteStudent" parameterType="int">
        DELETE FROM student
        WHERE sid = #{sid}
    </delete>
</mapper>
```

- StudentDemo

```java
package com.example;

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
            sqlSession.insert("com.example.StudentMapper.insertStudent", student);
            log.info("新增学生：{}", student);

            // 查询所有学生
            List<StudentDO> students = sqlSession.selectList("com.example.StudentMapper.findAllStudents");
            log.info("所有学生：");
            students.forEach(s -> log.info("{}", s));

            // 修改学生记录
            student.setScore(95);
            sqlSession.update("com.example.StudentMapper.updateStudent", student);

            // 查询修改后的学生信息
            StudentDO student1 = sqlSession.selectOne("com.example.StudentMapper.findStudentBySid", 1);
            log.info("修改后的学生信息：{}", student1);

            // 删除学生记录
            sqlSession.delete("com.example.StudentMapper.deleteStudent", 1);

            // 查询所有学生
            students = sqlSession.selectList("com.example.StudentMapper.findAllStudents");
            log.info("所有学生：");
            students.forEach(s -> log.info("{}", s));
        }
    }
}
```

## 运行结果

![image-20240412203345606](https://picgo-1314385327.cos.ap-guangzhou.myqcloud.com/markdown/image-20240412203345606.png)