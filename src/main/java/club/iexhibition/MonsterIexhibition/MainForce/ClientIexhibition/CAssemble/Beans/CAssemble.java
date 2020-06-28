package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "召集对象")
public class CAssemble {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "userTickName",value = "召集人名称")
    private String userTickName;
    @ApiModelProperty(name = "userAvatar",value = "召集人头像")
    private String userAvatar;
    @ApiModelProperty(name = "userId",value = "发起召集者id")
    private String userId;
    @ApiModelProperty(name = "title",value = "召集标题")
    private String title;
    @ApiModelProperty(value = "召集人联系方式")
    private String phoneNum;
    @ApiModelProperty(name = "address",value = "地址")
    private String address;
    @ApiModelProperty(name = "url",value = "召集封面")
    private String url;
    @ApiModelProperty(name = "isFollow",value = "是否关注")
    private Boolean isFollow=true;
    @JsonIgnore
    private String toAssembleUserId ;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(name = "assembleTime",value = "召集时间")
    private Date assembleTime;
    @ApiModelProperty(name = "expenses",value = "召集费用")
    private BigDecimal expenses;
    @ApiModelProperty(name = "sunNum",value = "召集总人数")
    private int sumNum;
    @ApiModelProperty(name = "num",value = "参与召集人数")
    private int num;
    @ApiModelProperty(value = "热度")
    private int hotNum;
    @ApiModelProperty(name = "cheerNum",value = "打榜次数")
    private int cheerNum;
    @ApiModelProperty(name = "content",value = "召集需知")
    private String content;
    @ApiModelProperty(name = "details",value = "召集详情")
    private String details;
    @ApiModelProperty(name = "cAssembles",value = "该召集人其他召集")
    private List<CAssemble> cAssembles;
    @ApiModelProperty(value = "展览id")
    private String exhibitionId;
    @ApiModelProperty(name = "urls",value = "用户创建召集的相册集合")
    private List<String> urls;
    @JsonIgnore
    private String inAssembleUserId;
    @ApiModelProperty(value = "是否参与了此召集")
    private Boolean isInAssemble=true;

    private List<CAssembleAlbum> cAssembleAlbums;

    @JsonIgnore
    public List<CAssembleAlbum> getcAssembleAlbums() {
        return cAssembleAlbums;
    }
    @JsonProperty
    public void setcAssembleAlbums(List<CAssembleAlbum> cAssembleAlbums) {
        this.cAssembleAlbums = cAssembleAlbums;
    }
}
