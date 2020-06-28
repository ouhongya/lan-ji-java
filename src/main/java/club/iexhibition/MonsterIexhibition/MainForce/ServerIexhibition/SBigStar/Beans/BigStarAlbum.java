package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SBigStar.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "大咖相册对象")
@Data
public class BigStarAlbum {
    @ApiModelProperty(name = "id",value = "id")
    private String id;
    @ApiModelProperty(name = "url",value = "相册url")
    private String url;
    @ApiModelProperty(name = "urlSort",value = "图片排序字段")
    protected String urlSort;
    @ApiModelProperty(name = "bigStarId",value = "大咖id")
    private String bigStarId;

    @JsonIgnore
    public String getUrlSort() {
        return urlSort;
    }
    @JsonProperty
    public void setUrlSort(String urlSort) {
        this.urlSort = urlSort;
    }
}
