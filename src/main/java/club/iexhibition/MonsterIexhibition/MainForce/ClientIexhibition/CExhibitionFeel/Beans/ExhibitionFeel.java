package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel(value = "展评对象")
@Data
public class ExhibitionFeel {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(name = "avatar",value = "用户头像")
    private String avatar;
    @ApiModelProperty(name = "userNickName",value = "用户名称")
    private String userNickName;
    @ApiModelProperty(name = "title",value = "展评标题")
    private String title;
    @ApiModelProperty(name = "url",value = "展评头像")
    private String url;
    @ApiModelProperty(name = "content",value = "展评内容")
    private String content;
    @ApiModelProperty(value = "是否接受打赏  1不接受  2接受")
    private Integer isRewards;
    @ApiModelProperty(name = "upNum",value = "点赞次数")
    private int upNum;
    @ApiModelProperty(name = "commentNum",value = "评论次数")
    private int commentNum;
    @ApiModelProperty(name = "forWardNum",value = "转发次数")
    private int forWardNum;
    @ApiModelProperty(value = "展评图片集合")
    private List<Url> urls = new ArrayList<>();
    @JsonIgnore
    private List<String> sUrls;
    @ApiModelProperty(value = "用户点赞状态")
    private Boolean isUp=true;
    @JsonIgnore
    private String upUserId;
    @ApiModelProperty(value = "用户收藏状态")
    private Boolean isFollow=true;
    @JsonIgnore
    private String toAssembleUserId;
    @ApiModelProperty(value = "展览id")
    private String exhibitionId;
    @ApiModelProperty(value = "展览标题")
    private String exhibitionTitle;
    @ApiModelProperty(value = "展览头像")
    private String exhibitionUrl;
    @ApiModelProperty(value = "展评评论集合")
    private PageInfo<ExhibitionFeelComment> pageInfo;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createdTime;

    private List<ExhibitionFeelAlbum> exhibitionFeelAlbums;

    @JsonIgnore
    public List<ExhibitionFeelAlbum> getExhibitionFeelAlbums() {
        return exhibitionFeelAlbums;
    }
    @JsonProperty
    public void setExhibitionFeelAlbums(List<ExhibitionFeelAlbum> exhibitionFeelAlbums) {
        this.exhibitionFeelAlbums = exhibitionFeelAlbums;
    }
}
