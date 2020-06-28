package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "区域对象")
public class Area {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "区域名称")
    private String name;
    @ApiModelProperty(name = "cityId",value = "城市id")
    private String cityId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createdTime;
}
