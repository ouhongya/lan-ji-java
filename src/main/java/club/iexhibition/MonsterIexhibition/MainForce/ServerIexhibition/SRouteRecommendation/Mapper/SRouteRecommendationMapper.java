package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteC;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface SRouteRecommendationMapper {

    List<RouteC> findRouteC(@Param("search") String search);


    List<Exhibition> findRecomExhibition(@Param("rid") String rid,@Param("search") String search);

    List<String> findTagNameByEid(String id);


    void addRouteR(@Param("id") String uuid,@Param("rid") String id,@Param("exhibitionId") String exhibitionId,@Param("time") String time,@Param("date") Date date);


    void updateRouteIsAttend(@Param("id") String id,@Param("isAttend") int i);
}
