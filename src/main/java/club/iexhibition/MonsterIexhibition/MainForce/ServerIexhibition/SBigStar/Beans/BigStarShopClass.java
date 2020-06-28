package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "大咖商品分类对象")
public class BigStarShopClass {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "分类名称")
    private String name;
    @ApiModelProperty(name = "num",value = "分类下的商品数量")
    private Integer num;
}
