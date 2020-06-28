package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "举报者名称+举报原因组合对象")
public class Hulk {
    @ApiModelProperty(value = "举报者名称")
    private String name;
    @ApiModelProperty(value = "举报原因")
    private String reason;
    @ApiModelProperty(value = "举报时间")
    private Date createdTime;
}
