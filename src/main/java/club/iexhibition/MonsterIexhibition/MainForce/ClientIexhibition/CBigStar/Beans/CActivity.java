package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "览集活动对象")
public class CActivity {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "title",value = "活动标题")
    private String title;
    @ApiModelProperty(name = "url",value = "活动头像")
    private String url;
    @ApiModelProperty(value = "活动详情图")
    private String detailsUrl;
    @ApiModelProperty(name = "startTime",value = "活动开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(name = "endTime",value = "活动结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @ApiModelProperty(name = "sumNum",value = "活动人数")
    private int sumNum;
    @ApiModelProperty(name = "price",value = "活动费用")
    private BigDecimal price;
    @ApiModelProperty(value = "活动规格")
    private String spec;
    @ApiModelProperty(value = "活动地址")
    private String address;
    @ApiModelProperty(value = "活动详情")
    private String details;
    @ApiModelProperty(value = "活动主办方")
    private String main;
    @ApiModelProperty(value = "活动合作方")
    private String cooperate;
}
