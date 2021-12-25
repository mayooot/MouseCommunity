package community.dto;

import community.model.User;
import lombok.Data;

/**
 * <p>项目文档： 贴子传输对象</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月25日 20:28:00
 */
@Data
public class PostDTO {
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
    private User user;
}
