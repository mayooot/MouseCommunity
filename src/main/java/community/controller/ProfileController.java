package community.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import community.dto.PostDTO;
import community.mapper.UserMapper;
import community.model.User;
import community.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>项目文档： 个人中心控制层</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月27日 13:16:00
 */
@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/profile/{action}")
    public String profile(Model model,
                          HttpServletRequest request,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize)  {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            System.out.println("我要返回主页了");
            return "redirect:/";
        }

        if ("posts".equals(action)) {
            model.addAttribute("section", "posts");
            model.addAttribute("sectionName", "我的贴子");
        }
        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }

        // 调用profileService查询该用户的贴子信息
        PageDTO<PostDTO> pageDTO = profileService.list(user.getAccountId(), pageNum, pageSize);

        // 调用PostService业务接口，获取分页后的信息
        model.addAttribute("pageDTO", pageDTO);
        return "profile";
    }
}
