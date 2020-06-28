package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.Assemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Beans.AssembleDetails;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Mapper.AssembleMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SAssemble.Service.AssembleService;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.City;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AssembleServiceImpl implements AssembleService {

    @Autowired
    private AssembleMapper assembleMapper;

    /**
     * 查询召集
     * @param paramBean
     * @param status
     * @return
     */
    @Override
    public PageInfo<Assemble> findAllAssemble(ParamBean paramBean, Integer status,String cityId) throws Exception {
        //分页和排序
        String order=paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),order);
        List<Assemble> assembles=assembleMapper.findAllAssemble(paramBean,status,new Date(),cityId);
        PageInfo<Assemble> pageInfo = new PageInfo<>(assembles);
        return pageInfo;
    }


    /**
     * 查询召集详情
     * @param assembleId
     * @return
     */
    @Override
    public AssembleDetails findAssembleDetails(String assembleId) throws Exception{
       return assembleMapper.findAssembleDetails(assembleId);
    }


    /**
     * 通过展览id查询该展览下的召集
     * @param paramBean
     * @param eid
     * @param status
     * @return
     */
    @Override
    public PageInfo<Assemble> findAssembleByEid(ParamBean paramBean, String eid, Integer status) throws Exception {
        //分页和排序
        String order = paramBean.getSortField()+" "+paramBean.getSortWay();
        PageHelper.startPage(paramBean.getPage(),paramBean.getSize());
        List<Assemble> assembles=assembleMapper.findAssembleByEid(eid,status,paramBean.getSearch(),new Date());
        PageInfo<Assemble> pageInfo = new PageInfo<>(assembles);
        return pageInfo;
    }

    /**
     * 查询拥有召集的城市区域
     * @return
     */
    @Override
    public List<City> findCityAreaToAssemble() throws Exception {
       return assembleMapper.findCityAreaToAssemble();
    }
}
