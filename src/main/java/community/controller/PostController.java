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

    /**
     * 打开贴子详情页
     * @param id 贴子的id
     * @param model 用于设置attribute
     * @return 贴子的详情页包括贴子信息和用户信息
     */
    @GetMapping("/post/{id}")
    public String post(@PathVariable(name="id") Integer id, Model model) {
        PostDTO postDTO = postService.getById(id);
        // 累加阅读数
        postService.incView(id);
        model.addAttribute("post", postDTO);
        return "post";
    }

    /**
     * 通过标题搜索贴子
     * @param search
     * @return
     */
    @GetMapping("/post/search")
    public String search(@RequestParam(name="search", defaultValue = "") String search) {
        if (StrUtil.isNotBlank(search)) {

        } else {
            return "redirect:/";
        }
        return "redirect:/";
    }
}
