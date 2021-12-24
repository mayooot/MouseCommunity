package community.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import community.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月23日 20:13:00
 */
@Repository
@Mapper
public interface UserMapper extends BaseMapper<User> {
    // @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    // void insert(User user);
    //
    // @Select("select * from user where token = #{token}")
    // User findByToken(@Param("token") String token);
}
