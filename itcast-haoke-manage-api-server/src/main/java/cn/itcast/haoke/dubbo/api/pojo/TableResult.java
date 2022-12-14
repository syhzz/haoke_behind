package cn.itcast.haoke.dubbo.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TableResult<T> {
    private List<T> list;
    private Pagination pagination;
}
