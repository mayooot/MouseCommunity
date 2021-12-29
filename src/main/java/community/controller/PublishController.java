package community.controller;

import community.model.Post;
import community.model.User;
import community.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.HTML;

/**
 * <p>项目文档： 发布文章</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 18:28:00
 */
@Controller
public class PublishController {
    @Autowired
    private PublishService publishService;


    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name="id") Integer id, Model model) {
        Post post = publishService.edit(id);
        if (post != null) {
            model.addAttribute("title", post.getTitle());
            model.addAttribute("content", post.getContent());
            model.addAttribute("tag", post.getTag());
            model.addAttribute("id", post.getId());
            return "publish";
        }
        return "redirect:/";
    }

    @PostMapping("/publish")
    public String doPublish(@RequestParam(value = "title") String title,
                            @RequestParam(value = "content") String content,
                            @RequestParam(value = "tag") String tag,
                            @RequestParam(value = "id") Integer id,
                            HttpServletRequest request,
                            Model model) {

        // 保证发生错误后，页面仍能显示已经编辑的信息
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        model.addAttribute("tag", tag);

        // 如果标题或内容为空，则返回并设置响应的错误信息
        if (title == null || "".equals(title)) {
            model.addAttribute("message", "标题不能为空！");
            return "publish";
        }
        if (content == null || "".equals(content)) {
            model.addAttribute("message", "内容不能为空！");
            return "publish";
        }
        if (tag == null || "".equals(tag)) {
            model.addAttribute("message", "标签不能为空！");
            return "publish";
        }

        // 如果用户为空，则返回并设置响应的错误信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("message", "用户未登录！请登录后再发布帖子~");
            return "publish";
        }

        // 封装一个post对象
        Post post = new Post();
        post.setId(id);
        post.setTitle(title);
        post.setContent(content);
        post.setTag(tag);
        post.setCreator(user.getAccountId());
        post.setGmtCreate(System.currentTimeMillis());
        post.setGmtModified(post.getGmtCreate());
        publishService.createOrUpdate(post);
        model.addAttribute("message", "发布成功！去看看吧~");
        return "redirect:/";
    }
}
