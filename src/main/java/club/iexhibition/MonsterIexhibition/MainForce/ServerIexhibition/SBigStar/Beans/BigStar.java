package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@ApiModel(value = "大咖对象")
@Data
public class BigStar {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "大咖名称")
    private String name;
    @ApiModelProperty(name = "avatar",value = "大咖头像")
    private String avatar;
    @ApiModelProperty(name = "introduce",value = "大咖简介")
    private String introduce;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(value = "排序字段  越大越靠前")
    private int sSort;
    @ApiModelProperty(value = "1 本期大咖   0普通大咖")
    private int types;
}
