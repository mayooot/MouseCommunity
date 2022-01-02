package community.controller;

import community.Exception.CustomizeErrorCode;
import community.dto.CommentDTO;
import community.dto.ResultDTO;
import community.model.Comment;
import community.model.User;
import community.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月01日 16:10:00
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 回复的方法
     * @param commentDTO 将前端字段封装成的对象，包括parentId, content, commentator
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Object comment(@RequestBody CommentDTO commentDTO,
                          HttpServletRequest request) {

        // 从session中获取user，如果为空说明用户未登录，则返回错误信息
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return ResultDTO.errorOf(CustomizeErrorCode.NO_LOGIN);
        }

        // 封装一个comment对象
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        commentService.insert(comment);
        return  ResultDTO.okOf();
    }
}
