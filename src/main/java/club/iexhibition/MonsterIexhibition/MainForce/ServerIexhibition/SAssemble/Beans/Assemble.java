package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;


@ApiModel(value = "召集对象")
@Data
public class Assemble {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "author",value = "召集人发起者id")
    private String author;
    @ApiModelProperty(name = "name",value = "召集标题")
    private String name;
    @ApiModelProperty(name = "assembleUser",value = "召集发起者名称")
    private String authorName;
    @ApiModelProperty(name = "exhibitionId",value = "展览id")
    private String exhibitionId;
    @ApiModelProperty(name = "exhibitionName",value = "展览名称")
    private String exhibitionName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
}
