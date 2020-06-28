package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "大咖对象")
public class CBigStar {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "大咖名称")
    private String name;
    @ApiModelProperty(name = "url",value = "大咖头像")
    private String url;
    @ApiModelProperty(name = "introduce",value = "大咖简介")
    private String introduce;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @ApiModelProperty(name = "commentNum",value = "大咖点评次数")
    private int commentNum;
    @ApiModelProperty(name = "urls",value = "大咖作品集合")
    private List<String> urls;
    @ApiModelProperty(name = "pageInfo",value = "其他大咖集合")
    private List<CBigStar> cBigStars;
}
