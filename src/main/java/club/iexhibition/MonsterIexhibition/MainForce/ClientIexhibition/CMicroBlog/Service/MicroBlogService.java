package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Service;

import club.iexhibition.MonsterIexhibition.Common.Beans.ParamBean;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.AddMicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogComment;
import com.github.pagehelper.PageInfo;

public interface MicroBlogService {
    PageInfo<MicroBlog> findMicroBlog(ParamBean paramBean,String sessionId);

    MicroBlog findMiCroBlogById(String id,ParamBean paramBean,String sessionId);

    String addMicroBlog(AddMicroBlog addMicroBlog, String sessionId);

    void microBlogComment(MicroBlogComment microBlogComment,String sessionId);

    void deleteMicroBlogComment(String id);

    void microBlogUp(String sessionId, String mid);

    void microBlogForWard(String sessionId, String mid);
}
