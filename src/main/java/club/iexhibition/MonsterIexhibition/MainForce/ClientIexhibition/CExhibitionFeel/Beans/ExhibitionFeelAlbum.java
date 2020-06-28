package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "展评图片集合对象")
public class ExhibitionFeelAlbum {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "图片url")
    private String url;
    @ApiModelProperty(value = "图片排序字段")
    private int urlSort;
}
