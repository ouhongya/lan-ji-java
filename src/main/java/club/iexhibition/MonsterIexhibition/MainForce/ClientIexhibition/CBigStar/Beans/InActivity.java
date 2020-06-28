package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class InActivity {
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "活动id")
    private String activityId;
    @ApiModelProperty(value = "称呼")
    private String tickName;
    @ApiModelProperty(value = "电话")
    private String phoneNum;
    @ApiModelProperty(value = "报名人数")
    private String signUpNum;
    @ApiModelProperty(value = "总共费用")
    private BigDecimal sumExpenses;
}
