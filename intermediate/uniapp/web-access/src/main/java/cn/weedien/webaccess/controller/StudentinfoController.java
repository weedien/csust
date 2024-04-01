package cn.weedien.webaccess.controller;

import cn.weedien.webaccess.entity.Studentinfo;
import cn.weedien.webaccess.service.StudentinfoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author weedien
 * @since 2023-12-15
 */
@RestController
@RequestMapping("/studentinfo")
public class StudentinfoController {

    @Resource
    private StudentinfoService studentinfoService;

    @GetMapping("/all")
    public List<Studentinfo> getAll() {
        return studentinfoService.list();
    }

    @GetMapping("/student/{id}")
    public Studentinfo getStudentById(@PathVariable Integer id) {
        return studentinfoService.getById(id);
    }

    @PostMapping("/student")
    public Map<String, Object> insertStudentinfo(@RequestBody Studentinfo studentinfo) {
        return studentinfoService.save(studentinfo) ? Map.of("status", "success") : Map.of("status", "fail");
    }

    // 更新学生信息
    @PutMapping("/student")
    public Map<String, Object> updateStudentinfo(Studentinfo studentinfo) {
        return studentinfoService.updateById(studentinfo) ? Map.of("status", "success") : Map.of("status", "fail");
    }

}
