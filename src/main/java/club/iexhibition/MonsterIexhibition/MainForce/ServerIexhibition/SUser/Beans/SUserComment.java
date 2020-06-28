package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "WX用户评论对象")
public class SUserComment {
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "展评/分享头像")
    private String avatar;
    @ApiModelProperty(value = "展评/分享标题")
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
}
