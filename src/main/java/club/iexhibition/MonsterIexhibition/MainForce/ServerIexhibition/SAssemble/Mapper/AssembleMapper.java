package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Mapper;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.Assemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.AssembleDetails;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.City;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AssembleMapper {
    List<Assemble> findAllAssemble(@Param("paramBean") ParamBean paramBean, @Param("status") Integer status,@Param("date") Date date,@Param("cityId") String cityId);

    AssembleDetails findAssembleDetails(String assembleId);

    List<Assemble> findAssembleByEid(@Param("eid") String eid,@Param("status") Integer status,@Param("search") String search,@Param("date") Date date);

    List<City> findCityAreaToAssemble();

    void updateAssembleStatus(@Param("eid") String eid,@Param("status") Integer status);
}
