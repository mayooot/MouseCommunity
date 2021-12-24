package community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import community.mapper.UserMapper;
import community.model.User;
import community.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * <p>项目文档： 首页接口</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月22日 15:44:00
 */
@Controller
public class IndexController {
  @Autowired
  private IndexService indexService;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        // 调用indexService业务接口
        indexService.index(request);

        return "index";
    }
}
