package community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import community.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月31日 19:07:00
 */
@Repository
@Mapper
public interface PostMapper extends BaseMapper<Post> {
}