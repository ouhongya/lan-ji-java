package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "大咖商品对象")
public class BigStarShop {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "商品名称")
    private String name;
    @ApiModelProperty(name = "url",value = "商品图片url")
    private String url;
    @ApiModelProperty(name = "description",value = "商品描述")
    private String description;
    @ApiModelProperty(name = "detailsUrl",value = "商品详情图")
    private String detailsUrl;
    @ApiModelProperty(name = "bigStarShopClassId",value = "商品分类id")
    private String bigStarShopClassId;
    @ApiModelProperty(name = "bigStarShopClassName",value = "商品分类名称")
    private String bigStarShopClassName;
    @ApiModelProperty(name = "starShopSpecs",value = "商品规格集合")
    private List<BigStarShopSpec> starShopSpecs;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;
    @ApiModelProperty(name = "status",value = "商品状态  1上架   0下架")
    private Integer status;
}
