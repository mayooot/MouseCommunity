package community.controller;

import community.dto.AccessTokenDTO;
import community.service.AuthorizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    private AuthorizeService authorizeService;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           HttpServletRequest request,
                           HttpServletResponse response) throws IOException {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);

        // 调用authorizeService业务接口
        authorizeService.callback(request, response, accessTokenDTO);

        // 解决重定向后uri参数携带JSESSIONID
        // return "redirect:/";
        response.sendRedirect("/");
        return null;
    }
}
