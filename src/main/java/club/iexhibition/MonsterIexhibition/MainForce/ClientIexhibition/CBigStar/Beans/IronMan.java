package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "大咖首页对象")
public class IronMan {
    @ApiModelProperty(name = "cBigStar",value = "大咖列表")
    private CBigStar cBigStar;
    @ApiModelProperty(name = "goods",value = "览集推荐商品")
    private List<Goods> goods;
    @ApiModelProperty(name = "pageInfo",value = "览集活动")
    private PageInfo<CActivity> pageInfo;
}
