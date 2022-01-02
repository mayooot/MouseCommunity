package community.dto;

import lombok.Data;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月01日 17:45:00
 */
@Data
public class CommentDTO {
    private Integer parentId;
    private String content;
    private Integer type;
}
