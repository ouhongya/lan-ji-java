package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SActivity.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "后台活动对象")
public class SActivity {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "活动名称")
    private String name;
    @ApiModelProperty(value = "活动封面图")
    private String url;
    @ApiModelProperty(value = "活动详情图")
    private String detailsUrl;
    @ApiModelProperty(value = "开始时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date endTime;
    @ApiModelProperty(value = "活动费用")
    private BigDecimal price;
    @ApiModelProperty(value = "费用规格")
    private String spec;
    @ApiModelProperty(value = "活动地址")
    private String address;
    @ApiModelProperty(value = "活动详情")
    private String details;
    @ApiModelProperty(value = "活动主办方")
    private String main;
    @ApiModelProperty(value = "活动合作方")
    private String cooperate;
    @ApiModelProperty(value = "活动人数")
    private int sumNum;
    @ApiModelProperty(value = "参与活动的人数")
    private int num;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(value = "活动上下架状态  1上架   2下架")
        private Integer state;
    @JsonIgnore
    private Integer status;
}
