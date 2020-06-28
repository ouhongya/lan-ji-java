package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "举报评论对象-后台")
public class TipOffComment {
    @ApiModelProperty(value = "举报的评论内容")
    private String tipOffContent;
    @ApiModelProperty(value = "被举报者名称")
    private String name;
    @ApiModelProperty(value = "评论的展评或分享内容")
    private String content;
    @ApiModelProperty(value = "评论的展品或分享图片")
    private List<String> contentUrls;
    @ApiModelProperty(value = "举报次数")
    private Integer tipOffNum;
    @ApiModelProperty(value = "举报人+举报原因对象集合")
    List<Hulk> hulks;
    @ApiModelProperty(value = "1展评   2分享")
    private String tableName;
    @JsonIgnore
    private String fromId;
    @ApiModelProperty(value = "评论发表时间")
    private Date createdTime;
    @ApiModelProperty(value = "评论id")
    private String efcId;
}
