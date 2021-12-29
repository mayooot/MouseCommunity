package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.mapper.PostMapper;
import community.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 18:28:00
 */
@Service
public class PublishService {
    @Autowired
    private PostMapper postMapper;

    public void addPost(Post post) {
        postMapper.insert(post);
    }

    public Post edit(Integer id) {
        Post post = null;
        try {
            post = postMapper.selectById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return post;
    }

    public void createOrUpdate(Post post) {
        if (post.getId() == null) {
            // 创建
            postMapper.insert(post);
        } else {
            // 更新
            QueryWrapper<Post> wrapper = new QueryWrapper<>();
            wrapper.eq("id", post.getId());
            postMapper.update(post, wrapper);
        }
    }
}
