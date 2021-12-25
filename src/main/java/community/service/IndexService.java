package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.mapper.UserMapper;
import community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * <p>项目文档： Index业务层</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 15:53:00
 */
@Service
public class IndexService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 如果cookie中已经存在设置的token，则与数据库中的数据比对，
     * 如果数据库中存在该条记录则将该用户的数据存入session，避免重复请求登录，浪费系统资源
     * @param request
     */
    public void index(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String token = cookie.getValue();
                    QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("token", token);
                    User user = userMapper.selectOne(wrapper);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

    }

}
