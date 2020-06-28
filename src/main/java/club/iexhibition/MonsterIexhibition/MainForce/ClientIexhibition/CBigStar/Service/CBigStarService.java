package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CBigStar.Beans.*;
import com.github.pagehelper.PageInfo;

public interface CBigStarService {
    IronMan findIronMan(ParamBean paramBean);

    PageInfo<Goods> findGoods(ParamBean paramBean);

    PageInfo<CActivity> findActivity(ParamBean paramBean);

    CBigStar findBigStarById(String id);

    PageInfo<CBigStar> findBigStar(ParamBean paramBean);

    CActivity findActivityById(String id);

    boolean inActivity(InActivity inActivity,String sessionId);
}
