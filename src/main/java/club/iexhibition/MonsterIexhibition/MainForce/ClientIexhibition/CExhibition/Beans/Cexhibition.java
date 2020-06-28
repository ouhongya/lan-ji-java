package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@ApiModel(value = "小程序展览对象")
@Data
public class Cexhibition {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "url",value = "展览头像url")
    private String url;
    @ApiModelProperty(name = "title",value = "展览标题")
    private String title;
    @ApiModelProperty(name = "startDate",value = "展览开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;
    @ApiModelProperty(name = "endDate",value = "展览结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date endDate;
    @ApiModelProperty(name = "address",value = "展览地址")
    private String address;
    @ApiModelProperty(name = "intro",value = "展览介绍")
    private String intro;
    @ApiModelProperty(name = "isFollow",value = "该用户是否关注  true关注  false  未关注")
    private Boolean isFollow=true;
    @JsonIgnore
    private String hallId;
    @JsonIgnore
    private String hallAddress;
    @ApiModelProperty(name = "remarks",value = "备注")
    private String remarks;
    @ApiModelProperty(name = "details",value = "展览详情介绍")
    private String details;
    @ApiModelProperty(name = "hallName",value = "展馆名称")
    private String hallName;
    @ApiModelProperty(name = "price",value = "展览规格金额")
    private String price;
    @ApiModelProperty(name = "elseExhibitions",value = "该展馆其他展览")
    List<Cexhibition> elseExhibitions;
    @JsonIgnore
    private String exhibitionHallId;
}
