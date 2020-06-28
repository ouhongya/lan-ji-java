package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SExhibition.Beans.Exhibition;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteC;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SRouteRecommendation.Beans.RouteR;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface SRouteRecommendationService {
    PageInfo<RouteC> findRecom(ParamBean paramBean) throws Exception;

    PageInfo<Exhibition> findRecomExhibition(String rid, ParamBean paramBean) throws Exception;

    void optionEnd(String id, List<RouteR> routeRs) throws Exception;
}
