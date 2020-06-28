package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.*;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface SExhibitionService {
    void addExhibition(Exhibition exhibition) throws Exception;

    Boolean addCity(String name) throws Exception;

    boolean addArea(Area area) throws Exception;

    boolean addExhibitionHall(ExhibitionHall exhibitionHall) throws Exception;

    boolean addExhibitionTag(String name) throws Exception;

    PageInfo<City> findAllCity(ParamBean paramBean) throws Exception;

    PageInfo<Area> findAreaByCid(String cid,ParamBean paramBean) throws Exception;

    boolean deleteCity(String cid) throws Exception;

    PageInfo<ExhibitionHall> findAllExhibitionHall(ParamBean paramBean) throws Exception;

    boolean deleteArea(String aid);

    boolean deleteExhibitionHall(String hid) throws Exception;

    void exhibitionUpDown(String eid, Integer status) throws Exception;

    boolean updateCity(String cid, String cname) throws Exception;

    boolean updateArea(String aid, String aname);

    boolean updateExhibitionHall(ExhibitionHall exhibitionHall) throws Exception;

    PageInfo<ExhibitionTag> findAllExhibitionTag(ParamBean paramBean) throws Exception;

    boolean updateExhibitionTag(String tid,String tname) throws Exception;

    void deleteExhibitionTag(String tid) throws Exception;

    PageInfo<Exhibition> findAllExhibition(ParamBean paramBean,Integer status,Integer timeStatus);

    void updateExhibition(Exhibition exhibition) throws Exception;

    void updateEAvatar(String id, String avatar) throws Exception;

    void deleteExhibition(String eid) throws Exception;

    List<City> findCityArea() throws Exception;

    String findExhibitionDetails(String eid) throws Exception;

    void updateExhibitionDetails(String eid, String details) throws Exception;

    Exhibition findExhibitionById(String eid) throws Exception;

    List<City> findCityAreaToExhibitionHall();

    List<ExhibitionHall> findExhibitionHallByAid(String aid);
}
