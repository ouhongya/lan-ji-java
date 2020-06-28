package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "召集对象")
@Data
public class AssembleDetails {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "author",value = "召集发起者的用户id")
    private String author;
    @ApiModelProperty(name = "authorName",value = "召集发起者的用户名称")
    private String authorName;
    @ApiModelProperty(name = "name",value = "召集标题")
    private String name;
    @ApiModelProperty(name = "exhibitionId",value = "展览id")
    private String exhibitionId;
    @ApiModelProperty(name = "exhibitionName",value = "展览名称")
    private String exhibitionName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "assembleTime",value = "召集时间")
    private Date assembleTime;
    @ApiModelProperty(name = "expenses",value = "费用")
    private BigDecimal expenses;
    @ApiModelProperty(name = "address",value = "召集地址")
    private String address;
    @ApiModelProperty(name = "assemblePeopleNum",value = "召集人数")
    private Integer assemblePeopleNum;
    @ApiModelProperty(name = "assembleSignPeopleNum",value = "已经参与召集人数")
    private Integer assembleSignPeopleNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(name = "createdTime",value = "创建时间")
    private Date createdTime;

}

