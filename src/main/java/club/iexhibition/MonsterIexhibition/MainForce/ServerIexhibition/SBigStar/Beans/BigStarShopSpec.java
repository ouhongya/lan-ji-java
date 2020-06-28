package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
@ApiModel(value = "商品规格对象")
public class BigStarShopSpec {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "name",value = "规格名称")
    private String name;
    @ApiModelProperty(name = "price",value = "单价")
    private BigDecimal price;
}
