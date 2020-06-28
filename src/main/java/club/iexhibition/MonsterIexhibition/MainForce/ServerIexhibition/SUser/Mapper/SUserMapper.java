package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUser;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SUserMapper {
    List<SUser> findAllUser(@Param("search") String search);

    SUser findSUserById(String id);

    List<SUserComment> findUserCommentByUid(String id);

    List<SUserAssemble> findInAssembleByUid(String openId);

    List<SUserAssemble> findAssembleByUid(String openId);
}
