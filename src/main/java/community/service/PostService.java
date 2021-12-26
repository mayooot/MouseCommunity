package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import community.dto.PaginationDTO;
import community.dto.PostDTO;
import community.mapper.PostMapper;
import community.mapper.UserMapper;
import community.model.Post;
import community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月25日 20:16:00
 */
@Service
public class PostService {
    @Autowired
    private PostMapper postMapper;

    @Autowired
    private UserMapper userMapper;

    public PageDTO page(Integer pageNum, Integer pageSize) {
       // 使用mp的分页查询，第pageNum页，pageSize条的记录,记录包括一些分页的信息
        Page<Post> records = postMapper.selectPage(new Page<>(pageNum, pageSize), null);
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


        // // 通过postMapper获取所有的贴子
        // List<Post> posts = postMapper.selectList(null);
        // List<PostDTO> postDTOList = new ArrayList<>();
        // PaginationDTO paginationDTO = new PaginationDTO();
        // for (Post post : posts) {
        //     QueryWrapper<User> wrapper = new QueryWrapper<>();
        //     wrapper.eq("id", post.getCreator());
        //     User user = userMapper.selectOne(wrapper);
        //     PostDTO postDTO = new PostDTO();
        //     BeanUtils.copyProperties(post, postDTO);
        //     postDTO.setUser(user);
        //     postDTOList.add(postDTO);
        // }
        // Page<Post> postPage = postMapper.selectPage(new Page<>(pageNum, pageSize), null);
        // paginationDTO.setPostDTOList(postDTOList);

        return pageDTO;
    }

}
