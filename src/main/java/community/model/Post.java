package community.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月24日 21:15:00
 */
@TableName("post")
@Data
public class Post {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private String creator;
    private Long commentCount;
    private Long viewCount;
    private Long likeCount;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
}
