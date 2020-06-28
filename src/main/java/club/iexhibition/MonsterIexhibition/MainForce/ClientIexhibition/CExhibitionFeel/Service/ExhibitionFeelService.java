package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeel;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelComment;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
import com.github.pagehelper.PageInfo;

public interface ExhibitionFeelService {
    PageInfo<ExhibitionFeel> findExhibitionFeel(ParamBean paramBean, String uid);

    String addExhibitionFeel(AddMicroBlog addMicroBlog, String sessionId);

    ExhibitionFeel findExhibitionFeelById(String sessionId, String fid, ParamBean paramBean);

    void exhibitionFeelUp(String fid, String sessionId);

    void exhibitionFeelComment(ExhibitionFeelComment exhibitionFeelComment, String sessionId);

    void deleteExhibitionFeelComment(String id, String sessionId);

    void exhibitionFeelForward(String id, String sessionId);
}
