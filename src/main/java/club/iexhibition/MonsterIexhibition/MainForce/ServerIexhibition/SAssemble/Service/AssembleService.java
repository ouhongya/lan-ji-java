package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.Assemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.AssembleDetails;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.City;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AssembleService {
    PageInfo<Assemble> findAllAssemble(ParamBean paramBean, Integer status,String cityId) throws Exception;

    AssembleDetails findAssembleDetails(String assembleId) throws Exception;

    PageInfo<Assemble> findAssembleByEid(ParamBean paramBean, String eid, Integer status) throws Exception;

    List<City> findCityAreaToAssemble() throws Exception;
}
