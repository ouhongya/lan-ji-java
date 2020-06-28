package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUser;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserAssemble;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SUser.Beans.SUserComment;
import com.github.pagehelper.PageInfo;

public interface SUserService {
    PageInfo<SUser> findAllUser(ParamBean paramBean);

    SUser findSUserById(String id);

    PageInfo<SUserComment> findCommentByUid(String id, ParamBean paramBean);

    PageInfo<SUserAssemble> findInAssembleByUid(String id, ParamBean paramBean);

    PageInfo<SUserAssemble> findAssembleByUid(String id, ParamBean paramBean);
}
