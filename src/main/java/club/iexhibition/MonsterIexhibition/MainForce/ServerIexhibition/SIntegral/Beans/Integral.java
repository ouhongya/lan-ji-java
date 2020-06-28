package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "积分对象")
public class Integral {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "1全部用户  2敬请期待")
    private Integer types;
    @ApiModelProperty(value = "发放通知")
    private String notice;
    @ApiModelProperty(value = "积分数量")
    private Integer num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonIgnore
    private String uid;
    @ApiModelProperty(value = "最后操作人账户名")
    private String username;
}
