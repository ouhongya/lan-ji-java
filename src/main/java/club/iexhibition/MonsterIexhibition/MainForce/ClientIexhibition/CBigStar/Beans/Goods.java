package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "览集推荐商品对象")
public class Goods {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "title",value = "商品名称")
    private String title;
    @ApiModelProperty(name = "url",value = "商品图")
    private String url;
    @ApiModelProperty(name = "price",value = "商品单价")
    private BigDecimal price;
    @ApiModelProperty(name = "sales",value = "商品销量")
    private int sales;
}
