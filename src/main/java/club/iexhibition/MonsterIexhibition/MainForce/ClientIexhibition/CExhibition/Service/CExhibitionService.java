package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans.Cexhibition;
import com.github.pagehelper.PageInfo;

public interface CExhibitionService {
    PageInfo<Cexhibition> findExhibition(ParamBean paramBean,String sessionId);

    Cexhibition findExhibitionById(String eid, String sessionId);

    void exhibitionAttention(String sessionId, String eid);
}
