package community.service;

import community.mapper.PostMapper;
import community.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

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
}
