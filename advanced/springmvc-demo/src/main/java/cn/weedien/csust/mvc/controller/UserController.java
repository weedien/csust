package cn.weedien.csust.mvc.controller;

import cn.weedien.csust.mvc.dao.UserMapper;
import cn.weedien.csust.mvc.po.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    @RequestMapping("/register")
    public ResponseEntity<Map<String, Object>> create(@RequestBody User user) {
        log.info("register: {}", user);
        userMapper.insert(user);
        return ResponseEntity.ok(Map.of("status", "ok"));
    }

    @RequestMapping("/list")
    public ResponseEntity<Map<String, Object>> list() {
        return ResponseEntity.ok(Map.of("data", userMapper.list()));
    }

    @RequestMapping("/getUserInfo")
    public ResponseEntity<Map<String, Object>> get(String username) {
        return ResponseEntity.ok(Map.of("data", userMapper.selectOne(username)));
    }
}
