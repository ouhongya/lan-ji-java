package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "展评评论对象")
public class ExhibitionFeelComment {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

    private String exhibitionFeelId;

    @JsonIgnore
    public String getExhibitionFeelId() {
        return exhibitionFeelId;
    }
    @JsonProperty
    public void setExhibitionFeelId(String exhibitionFeelId) {
        this.exhibitionFeelId = exhibitionFeelId;
    }
}
