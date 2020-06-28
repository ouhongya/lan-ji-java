package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.ExhibitionTag;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
@ApiModel(value = "线路推荐对象")
public class RouteC {

   private String id;

   private String name;

   private String avatar;

   private String city;

   private List<ExhibitionTag> tags;

   private List<Integer> types;

   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   private Date exhibitionTime;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
   private Date createdTime;
}
