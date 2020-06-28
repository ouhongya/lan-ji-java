package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@ApiModel(value = "展览对象")
@Data
public class Exhibition {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "展览名称")
    private String name;
    @ApiModelProperty(name = "avatar",value = "展览封面")
    private String avatar;
    @ApiModelProperty(name = "signUpWay",value = "报名方式")
    private String signUpWay;
    @ApiModelProperty(name = "startTime",value = "展览开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date startTime;
    @ApiModelProperty(name = "endTime",value = "展览结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date endTime;
    @ApiModelProperty(name = "exhibitionHallId",value = "展馆id")
    private String exhibitionHallId;
    @ApiModelProperty(name = "exhibitionType",value = "展览类型  1展览 2讲座  3书友会")
    private Integer exhibitionType;
    @ApiModelProperty(name = "status",value = "展览状态   0下架状态   1正常状态")
    private Integer status;
    @ApiModelProperty(name = "address",value = "展览地址")
    private String address;
    @ApiModelProperty(name = "expenses",value = "展览费用")
    private String expenses;
    @ApiModelProperty(name = "details",value = "展览详情")
    private String details;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone ="GMT+8")
    private Date createdTime;
    @ApiModelProperty(name = "tags",value = "标签Id集合")
    private List<String> tags;
    @ApiModelProperty(name = "tags",value = "标签对象集合")
    private List<ExhibitionTag> tagList;
    @ApiModelProperty(name = "hallName",value = "展馆名称")
    private String hallName;
    @ApiModelProperty(name = "intro",value = "介绍")
    private String intro;

    @JsonIgnore
    public List<String> getTags() {
        return tags;
    }
    @JsonProperty
    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
