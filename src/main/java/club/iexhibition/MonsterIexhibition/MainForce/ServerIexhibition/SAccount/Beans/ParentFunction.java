package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAccount.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(value = "一级功能对象")
@Data
public class ParentFunction {
    @ApiModelProperty(name = "pfName",value = "一级功能名称")
    private String pfName;
    @ApiModelProperty(name = "functions",value = "二级功能集合")
    private List<Function> functions;
}
