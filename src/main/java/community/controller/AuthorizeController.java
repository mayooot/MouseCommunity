package community.controller;

import community.dto.AccessTokenDTO;
import community.dto.GithubUser;
import community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>项目文档： github授权接口</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月22日 22:17:00
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id("7d8494545673126c8fa5");
        accessTokenDTO.setClient_secret("be2653e354985a2cfb8e2a49aa88de8280d75662");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri("http://localhost/callback");
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
