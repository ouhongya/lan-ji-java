package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.WxLogin.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "WX用户对象")
public class User {
    @ApiModelProperty(value = "openId")
    private String id;
    @ApiModelProperty(value = "用户昵称")
    private String name;
    @ApiModelProperty(value = "用户头像url")
    private String avatar;
    @ApiModelProperty(value = "用户所在城市")
    private String city;
    @ApiModelProperty(value = "用户所在国家")
    private String country;
    @ApiModelProperty(value = "用户性别 1男  2女")
    private Integer gender;
    @ApiModelProperty(value = "用户使用语言")
    private String language;
    @ApiModelProperty(value = "用户所在省份")
    private String province;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
}
