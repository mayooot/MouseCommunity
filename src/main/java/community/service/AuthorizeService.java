package community.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.dto.AccessTokenDTO;
import community.dto.GithubUser;
import community.mapper.UserMapper;
import community.model.User;
import community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>项目文档： github授权业务层</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 16:03:00
 */
@Service
public class AuthorizeService {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    /**
     * github授权返回业务
     * @param request
     * @param response
     * @param accessTokenDTO
     */
    public void  callback(HttpServletRequest request, HttpServletResponse response, AccessTokenDTO accessTokenDTO) {
        // 对accessTokenDTO设置参数
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);

        // 通过githubProvider对象获取accessToken 和 授权成功后的github用户对象
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);

        // 如果获取的github对象不为空，则封装一个对象实体，插入到数据库中
        if (githubUser != null && githubUser.getId() != null) {
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUser.getName());
            user.setAccountId(String.valueOf(githubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(githubUser.getAvatarUrl());
            user.setBio(githubUser.getBio());

            // 通过account_id查询用户表，如果该记录存在则更新该用户信息，如果不存在则插入
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("account_id", githubUser.getId());
            User record = userMapper.selectOne(wrapper);

            if (record != null) {
                // 更新
                userMapper.update(user, wrapper);
            } else {
                // 插入
                userMapper.insert(user);
            }

            // 将token写入cookie并将user写入session
            response.addCookie(new Cookie("token", token));
        }
    }
}
