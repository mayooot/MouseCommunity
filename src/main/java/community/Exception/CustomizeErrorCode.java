package community.Exception;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月31日 19:29:00
 */
public enum CustomizeErrorCode implements ICustomizeErrorCode{
    /**
     * 贴子未找到
     */
    POST_NOT_FOUND(2001, "您要找的贴子不存在或已被删除~🤗"),
    TARGET_PARAM_NOT_FOUND(2002, "未选中任何贴子或评论进行回复~🤗"),
    NO_LOGIN(2003, "当前操作需要登录，请登录后再试试吧~🤗"),
    SYS_ERROR(2004, "服务器太热啦🥵，等会再访问吧~"),
    TYPE_PARAM_WRONG(2005, "评论类型错误或不存在！"),
    COMMENT_NOT_FOUND(2006, "您回复的评论不存在！")
    ;

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
