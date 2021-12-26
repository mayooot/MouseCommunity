package community.test;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import community.dto.PostDTO;
import community.mapper.PostMapper;
import community.mapper.UserMapper;
import community.model.Post;
import community.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.PipedReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
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

    @Test
    public void testPage() {
        Page<Post> postPage = postMapper.selectPage(new Page<>(2, 5), null);
        System.out.println("records：" + postPage.getRecords());
        System.out.println("pages：" + postPage.getPages());
        System.out.println("total：" + postPage.getTotal());
        System.out.println("size："+ postPage.getSize());
        System.out.println("current：" + postPage.getCurrent());
    }

    @Test
    public void testPageDTO() {
        // 使用mp的分页查询，第pageNum页，pageSize条的记录,记录包括一些分页的信息
        Page<Post> records = postMapper.selectPage(new Page<>(1, 5), null);
        // 使用mp内置的PageDTO，并在里面封装分页所需要的信息（当前页数，总页数..)和贴子具体信息
        PageDTO<PostDTO> pageDTO = new PageDTO<>();


        // PostDTO封装的是一个完整的贴子信息，包括用户信息和贴子信息（User, Post)
        List<PostDTO> postDTOList = new ArrayList<>();
        // records.getRecords获取的是不包含用户信息的贴子对象集合
        List<Post> posts = records.getRecords();
        // 通过循环posts，userMapper根据id索引，找出发帖人的信息并将他封装进PostDTO
        for (Post post : posts) {
            QueryWrapper<User> userWrapper = new QueryWrapper<>();
            userWrapper.eq("id", post.getCreator());
            User user = userMapper.selectOne(userWrapper);
            PostDTO postDTO = new PostDTO();
            postDTO.setUser(user);
            BeanUtils.copyProperties(post, postDTO);
            postDTOList.add(postDTO);
        }
        pageDTO.setRecords(postDTOList);
        pageDTO.setCurrent(records.getCurrent());
        pageDTO.setSize(records.getSize());
        pageDTO.setPages(records.getPages());
        pageDTO.setTotal(records.getTotal());

        System.out.println("pageDTO.getRecords()：" + pageDTO.getRecords());
        System.out.println();
        System.out.println("pageDTO.getCurrent()：" + pageDTO.getCurrent());
        System.out.println();
        System.out.println("pageDTO.getPages()：" + pageDTO.getPages());
    }

}
