package cn.weedien.csust.advanced.T8_mybatis;

import java.util.List;

public interface StudentMapper {
    void insertStudent(StudentDO student);

    List<StudentDO> findAllStudents();

    void updateStudent(StudentDO student);

    void deleteStudent(int id);

    StudentDO findStudentBySid(int id);

}
