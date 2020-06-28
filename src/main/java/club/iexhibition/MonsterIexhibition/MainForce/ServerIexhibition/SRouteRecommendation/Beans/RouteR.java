package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "线路推荐-返回WX")
@Data
public class RouteR {
    @ApiModelProperty(name = "exhibitionId",value = "展览id")
    private String exhibitionId;
    @ApiModelProperty(name = "time",value = "推荐观展时间段")
    private String time;
}
