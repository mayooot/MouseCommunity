package community.enums;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2022年01月01日 18:57:00
 */
public enum CommentTypeEnum {
    /**
     * POST(1): 贴子的回复
     * COMMENT(2)：贴子评论的回复
     */
    POST(1),
    COMMENT(2);
    private Integer type;

    public static boolean isExist(Integer type) {
        for (CommentTypeEnum commentTypeEnum : CommentTypeEnum.values()) {
            if (commentTypeEnum.getType() == type) {
                return true;
            }
        }
        return false;
    }

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type) {
        this.type = type;
    }
}
