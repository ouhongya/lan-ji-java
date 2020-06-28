package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "展馆对象")
@Data
public class ExhibitionHall {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "展馆名称")
    private String name;
    @ApiModelProperty(name = "areaId",value = "区域id")
    private String areaId;
    @ApiModelProperty(name = "num",value = "展馆下的展览数量")
    private Integer num;
    @ApiModelProperty(name = "areaName",value = "区域名称")
    private String areaName;
    @ApiModelProperty(name = "cityName",value = "城市名称")
    private String cityName;
    @ApiModelProperty(name = "address",value = "展馆详细地址")
    private String address;
    @ApiModelProperty(name = "route",value = "交通路线")
    private String route;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createdTime;
}
