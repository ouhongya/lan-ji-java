package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Service.Impl;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.Common.Utils.UUIDGenerator;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteC;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteR;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Mapper.SRouteRecommendationMapper;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Service.SRouteRecommendationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SRouteRecommendationServiceImpl implements SRouteRecommendationService {

    @Autowired
    private SRouteRecommendationMapper sRouteRecommendationMapper;


    /**
     * 查询请求推荐的用户信息和条件
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<RouteC> findRecom(ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<RouteC> routeCS = sRouteRecommendationMapper.findRouteC(paramBean.getSearch());

        PageInfo<RouteC> pageInfo = new PageInfo<>(routeCS);

        return pageInfo;
    }


    /**
     * 通过推荐id查询合适的展览
     * @param rid
     * @param paramBean
     * @return
     */
    @Override
    public PageInfo<Exhibition> findRecomExhibition(String rid, ParamBean paramBean) throws Exception {
        //分页和排序
        String orderBy=paramBean.getSortField()+" "+paramBean.getSortWay();

        PageHelper.startPage(paramBean.getPage(),paramBean.getSize(),orderBy);

        List<Exhibition> exhibitions=sRouteRecommendationMapper.findRecomExhibition(rid,paramBean.getSearch());

        //查询展览标签
        for (Exhibition exhibition : exhibitions) {

            List<String> tagNames=sRouteRecommendationMapper.findTagNameByEid(exhibition.getId());

            exhibition.setTags(tagNames);
        }

        PageInfo<Exhibition> pageInfo = new PageInfo<>(exhibitions);

        return pageInfo;
    }


    /**
     * 筛选合适的展览返回WX
     * @param id
     * @param routeRs
     */
    @Override
    public void optionEnd(String id, List<RouteR> routeRs) throws Exception {
        //添加筛选好的数据
        for (RouteR routeR : routeRs) {
            sRouteRecommendationMapper.addRouteR(UUIDGenerator.uuid(),id,routeR.getExhibitionId(),routeR.getTime(),new Date());
        }
        //修改线路推荐状态
        sRouteRecommendationMapper.updateRouteIsAttend(id,2);
    }
}
