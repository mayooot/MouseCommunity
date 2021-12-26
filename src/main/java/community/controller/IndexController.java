package community.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import community.dto.PostDTO;
import community.mapper.UserMapper;
import community.model.Post;
import community.model.User;
import community.service.IndexService;
import community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
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

  @Autowired
  private PostService postService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                        @RequestParam(name ="pageSize", defaultValue = "5") Integer pageSize) {
        // 调用IndexService业务接口
        indexService.index(request);

        // 调用PostService业务接口，获取所有贴子的信息
        PageDTO pageDTO = postService.page(pageNum, pageSize);
        // 调用PostService业务接口，获取分页后的信息
        model.addAttribute("pageDTO", pageDTO);
        return "index";
    }
}
