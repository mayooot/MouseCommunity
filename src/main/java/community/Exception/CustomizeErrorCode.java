package community.Exception;

import com.baomidou.mybatisplus.annotation.Version;

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
    POST_NOT_FOUND("您要找的贴子不存在或已被删除🤗");

    private String message;

    CustomizeErrorCode(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
