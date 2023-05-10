package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * 分页参数（必传）
 */
public class PageDto {
    @NotNull(message = "当前页不能为空")
    private Integer pageNum;
    @NotNull(message = "页码不能为空")
    private Integer pageSize;
}
