package club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ServerIexhibition.SComment.Beans.TipOffComment;
import com.github.pagehelper.PageInfo;

public interface CommentService {
    PageInfo<TipOffComment> findTipOffComment(ParamBean paramBean);
}
