package community.service;

import community.Exception.CustomizeErrorCode;
import community.Exception.CustomizeException;
import community.enums.CommentTypeEnum;
import community.mapper.CommentMapper;
import community.mapper.PostMapper;
import community.model.Comment;
import community.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月01日 17:48:00
 */
@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private PostMapper postMapper;


    /**
     * 将回复插入数据库
     * @param comment 回复对象
     */
    @Transactional
    public void insert(Comment comment) {
        if (comment.getParentId() == null || comment.getParentId() == 0) {
            throw new CustomizeException(CustomizeErrorCode.TARGET_PARAM_NOT_FOUND);
        }

        if (comment.getType() == null || !CommentTypeEnum.isExist(comment.getType())) {
            throw new CustomizeException(CustomizeErrorCode.TYPE_PARAM_WRONG);
        }

        if (comment.getType() == CommentTypeEnum.COMMENT.getType()) {
            // 回复评论
            Comment dbComment = commentMapper.selectById(comment.getParentId());
            if (dbComment == null) {
                throw  new CustomizeException(CustomizeErrorCode.COMMENT_NOT_FOUND);
            } else {
                commentMapper.insert(comment);
            }
        } else {
            // 回复贴子
            Post dbPost = postMapper.selectById(comment.getParentId());
            if (dbPost == null) {
                throw new CustomizeException(CustomizeErrorCode.POST_NOT_FOUND);
            }
            commentMapper.insert(comment);
            Long updatedCommentCount = dbPost.getCommentCount() + 1;
            dbPost.setCommentCount(updatedCommentCount);
            postMapper.updateById(dbPost);
        }
    }
}
