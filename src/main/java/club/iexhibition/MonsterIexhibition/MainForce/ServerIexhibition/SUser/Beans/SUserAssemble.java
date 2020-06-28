package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "WX用户召集对象")
public class SUserAssemble {
    @ApiModelProperty(value = "召集头像")
    private String avatar;
    @ApiModelProperty(value = "召集标题")
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createdTime;

}
