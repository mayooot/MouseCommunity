package community.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.mapper.PostMapper;
import community.mapper.UserMapper;
import community.model.Post;
import community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PipedReader;
import java.util.Arrays;
import java.util.List;

/**
 * <p>项目文档： test mybatis plus</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 14:16:00
 */
@SpringBootTest
public class MybatisPlusTest {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PostMapper postMapper;

    @Test
    public void testSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);

        List<Post> posts = postMapper.selectList(null);
        posts.forEach(System.out::println);


    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("token", "3");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }

}
