package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    /**
     * 数据
     */
    private List rows;
    /**
     * 总记录数
     */
    private Long total;
}
