package cn.itcast.haoke.dubbo.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName("tb_ad")
public class Ad implements Serializable {

    private static final long serialVersionUID = 2665307291931280909L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Integer type;

    private String title;

    private String url;
}
