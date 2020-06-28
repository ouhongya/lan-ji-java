package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.Url;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@ApiModel(value = "WX分享对象")
public class MicroBlog {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "用户昵称")
    private String userNickName;
    @ApiModelProperty(value = "用户头像url")
    private String avatar;
    @ApiModelProperty(value = "随笔标题")
    private String title;
    @ApiModelProperty(value = "随笔内容")
    private String content;
    @ApiModelProperty(value = "随笔图片集")
    private List<Url> urls = new ArrayList<>();
    @ApiModelProperty(value = "点赞次数")
    private int upNum;
    @ApiModelProperty(value = "评论次数")
    private int commentNum;
    @ApiModelProperty(value = "转发次数")
    private int forWardNum;
    @ApiModelProperty(value = "展评id  如果为1  代表这是一个分享")
    private String exhibitionId;
    @ApiModelProperty(value = "展评头像   分享没有头像")
    private String url;
    @ApiModelProperty(value = "随笔评论集合")
    private PageInfo<MicroBlogComment> pageInfo;

    @ApiModelProperty(value = "是否点过赞")
    private Boolean isUp=true;
    @JsonIgnore
    private String upUserId;
    @ApiModelProperty(value = "是否收藏")
    private Boolean isFollow = true;
    @JsonIgnore
    private String toAssembleUserId;


    private List<MicroBlogAlbum> microBlogAlbums;

    @JsonIgnore
    public List<MicroBlogAlbum> getMicroBlogAlbums() {
        return microBlogAlbums;
    }
    @JsonProperty
    public void setMicroBlogAlbums(List<MicroBlogAlbum> microBlogAlbums) {
        this.microBlogAlbums = microBlogAlbums;
    }
}
