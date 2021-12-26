package community.dto;

import community.model.Post;
import lombok.Data;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

/**
 * <p>项目文档： TODO</p>
 *
 * @author liming
 * @version 1.0.0
 * @createTime 2021年12月26日 15:28:00
 */
@Data
public class PaginationDTO {
    private List<PostDTO> postDTOList;
    // 当前页
    private Integer current;
    // 页数
    private Integer pages;

}
