package community.controller;

import cn.hutool.core.util.StrUtil;
import community.dto.PostDTO;
import community.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月28日 18:26:00
 */
@Controller
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/post/{id}")
    public String post(@PathVariable(name="id") Integer id, Model model) {
        PostDTO postDTO = postService.getById(id);
        model.addAttribute("post", postDTO);
        return "post";
    }

    @GetMapping("/post/search")
    public String search(@RequestParam(name="search", defaultValue = "") String search) {
        if (StrUtil.isNotBlank(search)) {

        } else {
            return "redirect:/";
        }
        return "redirect:/";
    }
}
