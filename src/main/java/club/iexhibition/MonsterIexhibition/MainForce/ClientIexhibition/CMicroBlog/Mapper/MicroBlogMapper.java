package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlog;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CMicroBlog.Beans.MicroBlogComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MicroBlogMapper {

    List<MicroBlog> findMicroBlog(@Param("search") String search,@Param("uid")String uid);

    MicroBlog findMicroBlogById(@Param("id") String id,@Param("uid") String uid);

    List<MicroBlogComment> findCommentByMid(@Param("mid") String id);

    void addMicroBlog(MicroBlog microBlog);

    void addMicroBlogAlbum(@Param("mid") String id, @Param("albums") List<MicroBlogAlbum> microBlogAlbums);

    void microBlogComment(MicroBlogComment microBlogComment);

    void updateMicroBlogCommentStatus(@Param("id") String id,@Param("status") int i);

    String findMicroBlogUp(@Param("uid") String uid,@Param("mid") String mid);

    void microBlogUp(@Param("uid") String uid,@Param("mid") String mid);

    void microBlogForWard(@Param("id") String uuid,@Param("uid") String uid,@Param("mid") String mid);

    void deleteMicroBlogUp(@Param("uid") String uid,@Param("mid") String mid);

    String findMicroBlogContent(String fromId);

    List<String> findMicroBlogAlbum(String fromId);
}
