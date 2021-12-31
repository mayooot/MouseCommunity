package community.Exception;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月31日 19:11:00
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(ICustomizeErrorCode errorCode) {
        this.message = errorCode.getMessage();
    }


    @Override
    public String getMessage() {
        return message;
    }
}
