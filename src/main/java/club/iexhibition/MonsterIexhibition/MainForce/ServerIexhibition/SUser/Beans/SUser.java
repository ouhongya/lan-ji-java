package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SIntegral.Beans.Integral;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "后台WX用户对象")
@Data
public class SUser {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "昵称")
    private String name;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "用户所在省份")
    private String province;
    @ApiModelProperty(value = "用户使用语言")
    private String language;
    @ApiModelProperty(value = "1男  2女")
    private Integer gender;
    @ApiModelProperty(value = "用户所在国家")
    private String country;
    @ApiModelProperty(value = "用户所在城市")
    private String city;
    @ApiModelProperty(value = "用户钱包")
    private BigDecimal money;
    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date createdTime;
}
