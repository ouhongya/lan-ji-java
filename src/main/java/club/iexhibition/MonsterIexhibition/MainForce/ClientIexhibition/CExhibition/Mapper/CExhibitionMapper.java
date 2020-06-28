package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibition.Beans.Cexhibition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CExhibitionMapper {
    List<Cexhibition> findExhibition(@Param("uid") String uid);

    Cexhibition findExhibitionById(@Param("eid") String eid,@Param("uid") String uid);

    String findExhibitionHallIdByEid(String eid);

    String findExhibitionAttention(@Param("uid") String uid,@Param("hallId") String hallId);

    void exhibitionAttention(@Param("uid") String uid,@Param("hallId") String hallId);

    void deleteExhibitionAttention(@Param("uid") String uid,@Param("hallId") String hallId);

    Cexhibition findExhibitionByIdAssemble(String id);

    String findExhibitionUrlById(String eid);
}
