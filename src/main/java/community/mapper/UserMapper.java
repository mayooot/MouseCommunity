package community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>项目文档： 用户mapper</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月23日 20:13:00
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
