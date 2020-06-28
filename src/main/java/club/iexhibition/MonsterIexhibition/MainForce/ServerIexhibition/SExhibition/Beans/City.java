package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "城市对象")
public class City {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "城市名称")
    private String name;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createdTime;
    @ApiModelProperty(name = "areas",value = "区域")
    private List<Area> areas;
}
