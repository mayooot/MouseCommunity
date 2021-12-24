package community.controller;

import com.fasterxml.jackson.annotation.JsonGetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>项目文档： 发布文章</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 18:28:00
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
