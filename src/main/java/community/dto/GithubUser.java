package community.dto;

import lombok.Data;

/**
 * <p>项目文档： 用户实体类</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月23日 12:55:00
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;

}
