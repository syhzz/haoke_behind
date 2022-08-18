package cn.itcast.haoke.dubbo.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndexAdResult {
    private List<IndexAdResultData> list;
}
