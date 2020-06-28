package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.CAssembleAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CAssemble.Beans.InAssemble;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CAssembleMapper {
    List<CAssemble> findAssemble(@Param("uid") String uid, @Param("date")Date date);

    CAssemble findAssembleById(@Param("id") String id,@Param("uid") String uid,@Param("date") Date date);

    List<String> findAssembleAlbum(@Param("aid") String id);

    List<CAssemble> findAssembleByEid(@Param("eid") String eid,@Param("uid") String uid);

    void addAssembleNoE(CAssemble cAssemble);

    void addAssembleAlbum(@Param("aid") String aid,@Param("urls") List<CAssembleAlbum> urls);

    void addAssemble(CAssemble cAssemble);

    String findAttention(@Param("uid") String uid,@Param("aUid") String aUid);

    String findAuthorByAid(String aid);

    void addAssembleAttention(@Param("uid") String uid,@Param("toUid") String toUid);

    void deleteAttention(@Param("aUid") String aUid,@Param("uid") String uid);

    Integer findCheerNum(String id);

    Integer findHotValue(String id);

    Map<String, Long> findDoubleAssembleUserNum(String aid);

    void inAssemble(InAssemble inAssemble);

    String findInAssemble(@Param("aid") String aid,@Param("uid") String uid);
}
