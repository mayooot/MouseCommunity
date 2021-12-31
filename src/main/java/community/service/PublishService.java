package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.Exception.CustomizeErrorCode;
import community.Exception.CustomizeException;
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


}
