package club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Mapper;

import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeel;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelAlbum;
import club.iexhibition.MonsterIexhibition.MainForce.ClientIexhibition.CExhibitionFeel.Beans.ExhibitionFeelComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExhibitionFeelMapper {
    List<ExhibitionFeel> findExhibitionFeel(@Param("search") String search,@Param("uid") String uid);

    void addExhibitionFeel(ExhibitionFeel exhibitionFeel);

    void addExhibitionFeelAlbum(@Param("feelId") String id, @Param("urls") List<ExhibitionFeelAlbum> exhibitionFeelAlbums);

    ExhibitionFeel findExhibitionFeelById(@Param("uid") String uid,@Param("fid") String fid);

    List<ExhibitionFeelComment> findExhibitionFeelComment(@Param("fid") String id);

    String findExhibitionFeelUp(@Param("fid") String fid,@Param("uid") String uid);

    void deleteExhibitionFeelUp(@Param("fid") String fid,@Param("uid") String uid);

    void addExhibitionFeelUp(@Param("fid") String fid,@Param("uid") String uid);

    String findExhibitionContent(String fromId);

    List<String> findExhibitionAlbum(String fromId);

    void exhibitionFeelComment(ExhibitionFeelComment exhibitionFeelComment);

    String findExhibitionFeelCommentId(@Param("id") String id, @Param("openId") String openId);

    void updateExhibitionFeelCommentStatus(@Param("id") String id,@Param("status") int i);

    void exhibitionFeelForward(@Param("id") String uuid, @Param("eid") String id, @Param("uid") String openId);
}
