package community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月01日 16:09:00
 */
@Repository
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
