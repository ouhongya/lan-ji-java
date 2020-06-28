package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.*;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import java.util.List;

public interface SExhibitionMapper {
    void addExhibition(@Param("exhibition") Exhibition exhibition);

    City findCityByName(String name);

    void addCity(@Param("uuid") String uuid,@Param("name") String name,@Param("date") Date date);

    Area findAreaByName(@Param("cityId") String cityId,@Param("name") String name);

    void addArea(Area area);

    ExhibitionHall findExhibitionHallByAidAndHname(@Param("areaId") String areaId,@Param("name") String name);

    void addExhibitionHall(ExhibitionHall exhibitionHall);

    ExhibitionTag findExhibitionTagByName(String name);

    void addExhibitionTag(@Param("uuid") String uuid,@Param("name") String name,@Param("date") Date date);

    List<City> findAllCity(@Param("search") String search);

    List<Area> findAllAreaByCid(String cid);

    void updateCityStatus(@Param("cid") String cid, @Param("status") int i);

    List<ExhibitionHall> findAllExhibitionHall(@Param("search") String search);

    List<ExhibitionHall> findExhibitionHallByAid(String aid);

    void updateAreaStatus(@Param("aid") String aid,@Param("status") int i);

    List<Exhibition> findExhibitionByHid(String hid);

    void updateExhibitionHallStatus(@Param("hid") String hid,@Param("status") int i);

    void updateExhibitionStatus(@Param("eid") String eid, @Param("status") Integer status);

    void updateCityName(@Param("cid") String cid, @Param("cname") String cname);

    Area findAreaById(String aid);

    Area findAreaByCidAndAname(@Param("cid") String cityId,@Param("aname") String aname);

    void updateAreaName(@Param("aid") String aid,@Param("aname") String aname);

    ExhibitionHall findExhibitionHallById(String id);

    void updateExhibitionHall(@Param("exhibitionHall") ExhibitionHall exhibitionHall);

    List<ExhibitionTag> findAllExhibitionTag(@Param("search") String search);

    void updateExhibitionTagName(@Param("tid") String tid,@Param("tname") String tname);

    void deleteETag_EX(String tid);

    void updateETagStatus(@Param("tid") String tid,@Param("status") int i);

    List<Exhibition> findAllExhibition(@Param("search") String search,@Param("status") Integer status,@Param("timeStatus") Integer timeStatus,@Param("date" ) Date date);

    void updateExhibition(@Param("exhibition") Exhibition exhibition);

    void updateEAvatar(@Param("id") String id, @Param("avatar") String avatar);

    List<City> findAllCityArea();

    String findExhibitionDetails(String eid);

    void updateExhibitionDetails(@Param("eid") String eid,@Param("details") String details);

    Exhibition findExhibitionById(String eid);

    void addTagToExhibition(@Param("eid") String id,@Param("tags") List<String> tags);

    void deleteTagByEid(String id);

    List<City> findCityAreaToExhibitionHall();
}
