package cn.weedien.csust.advanced.T5_hibernate_orm;

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
