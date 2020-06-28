package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "加入召集对象")
public class InAssemble {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户id")
    private String uid;
    @ApiModelProperty(value = "召集id")
    private String aid;
    @ApiModelProperty(value = "称呼")
    private String tickName;
    @ApiModelProperty(value = "电话号码")
    private String phoneNum;
}
