package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "分享评论对象")
public class MicroBlogComment {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户昵称")
    private String userTickName;
    @ApiModelProperty(value = "用户头像")
    private String avatar;
    @ApiModelProperty(value = "评论内容")
    private String content;
    @ApiModelProperty(value = "评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    private String microBlogId;

    @JsonIgnore
    public String getMicroBlogId() {
        return microBlogId;
    }
    @JsonProperty
    public void setMicroBlogId(String microBlogId) {
        this.microBlogId = microBlogId;
    }
}
