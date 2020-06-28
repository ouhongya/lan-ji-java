package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "召集图片对象")
public class CAssembleAlbum {

    private String id;
    @ApiModelProperty(value = "图片url")
    private String url;
    @ApiModelProperty(value = "图片排序字段")
    private int urlSort;
}
