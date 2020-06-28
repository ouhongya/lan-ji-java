package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.InAssemble;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface CAssembleService {
    PageInfo<CAssemble> findAssemble(ParamBean paramBean, String sessionId);

    CAssemble findAssembleById(String id, String sessionId);

    PageInfo<CAssemble> findAssembleByEid(String eid, ParamBean paramBean,String sessionId);

    String addAssemble(Map<String,Object> assembleMap,String sessionId) throws Exception;

    void assembleAttention(String aUid, String sessionId);

    boolean inAssemble(InAssemble inAssemble,String sessionId);
}
