package cn.weedien.csust.mvc;

import cn.weedien.csust.advanced.mvc.dao.UserMapper;
import cn.weedien.csust.advanced.mvc.po.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class ApiTest {


    @Resource
    private UserMapper userMapper;

    @Test
    void testInsertUser() {
        User user = new User("zhangming", "123456678", "776232374@qq.com", "17674661745");
        userMapper.insert(user);
        log.info("新增用户成功，用户名：{}", user.getUsername());
    }

    @Test
    void testSelectByUsername() {
        User user = userMapper.selectOne("zhangming");
        if (user != null) {
            log.info("查找到用户：{}", user);
        } else {
            log.warn("用户不存在");
        }
    }

    @Test
    void testSelectUsers() {
        List<User> users = userMapper.list();
        log.info("用户列表如下：");
        users.forEach(user -> log.info(user.toString()));
    }

}
