package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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


    public List<PostDTO> list() {
        List<Post> posts = postMapper.selectList(null);
        List<PostDTO> postDTOList = new ArrayList<>();
        for (Post post : posts) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("id", post.getCreator());
            User user = userMapper.selectOne(wrapper);
            PostDTO postDTO = new PostDTO();
            BeanUtils.copyProperties(post, postDTO);
            postDTO.setUser(user);
            postDTOList.add(postDTO);
        }

        return postDTOList;
    }
}
